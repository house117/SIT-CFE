/*////////////////////////////////////////////////////////////////*/
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!',
        tareas: [],
        opcion: 'login',
        verComentariosNombreTarea: '',
        tareaActual: {},
        colaboradorActual: {},
        tareaAEditar: '',
        colaboradores: [],
        comentarios: [],
        departamentos: [],
        colaboradorLogged: {},
        errorLogin: 'correcto',
    },
    mounted: function () {
        console.log('Descargando colaboradores');
        axios.get('/colaborador/read')
            .then(function (response) {
                // handle success
                this.app.colaboradores = response.data;
            })
        /*console.log('Descargando tareas');
        axios.get('/encargo/read')
            .then(function (response) {
                // handle success
                this.app.tareas = response.data;
            })*/
        console.log('Descargando departamentos');
        axios.get('/encargo/getdepto')
            .then(function (response) {
                // handle success
                this.app.departamentos = response.data;
            })
    },
    methods: {
        fn_categoria: function () {
            console.log("Estoy en categoría");

        },
        fn_enviar_colaborador: function () {
            var fd = new FormData();
            fd.append("usuario", document.forms['cat-form'].usuario.value);
            fd.append("contrasena", document.forms['cat-form'].contrasena.value);
            fd.append("nombre", document.forms['cat-form'].nombre.value);
            fd.append("apellido1", document.forms['cat-form'].apellido1.value);
            fd.append("apellido2", document.forms['cat-form'].apellido2.value);
            fd.append("puesto", document.forms['cat-form'].puesto.value);
            fd.append("departamento", document.forms['cat-form'].departamento.value.replace(/[^0-9]/g, ''));
            fd.append("fechaalta", document.forms['cat-form'].fechaalta.value);
            axios({
                    method: 'post',
                    url: 'colaborador/create',
                    responseType: 'json',
                    data: fd
                })
                .then(function (response) {
                    console.log(response.data);
                    this.app.colaboradores = response.data;
                });
            this.opcion = 'listaColaboradores';
        },
        fn_eliminar_colaborador: function (idcolaborador) {
            console.log("ID COLABORADOR A ELIMINAR ES: " + idcolaborador)
            axios.post('http://localhost:8080/colaborador/delete/?idcolaborador=' + idcolaborador)
                .then(function (response) {
                    // handle success
                    this.app.colaboradores = response.data;
                })
        },
        fn_prepare_colaborador_edition(colaborador) {
            console.log("Preparando colaborador");
            this.colaboradorActual = colaborador;
            this.opcion = 'updateColaborador';
        },
        fn_prepare_edition(tarea) {
            this.tareaActual = tarea;
            this.opcion = 'editarTarea';
        },
        fn_editar_colaborador() {
            console.log("Ejecutando función de editar colaborador")
            var fd = new FormData();
            console.log("el id encargo es: " + this.colaboradorActual.idcolaborador);
            axios.post('http://localhost:8080/colaborador/update/?' +
                "usuario=" + document.forms['col-form'].usuario.value +
                "&contrasena=" + document.forms['col-form'].contrasena.value +
                /*"&nombre=" + document.forms['col-form'].nombre.value +
                "&apellido1=" + document.forms['col-form'].apellido1.value +
                "&apellido2=" + document.forms['col-form'].apellido2.value +
                "&puesto=" + document.forms['col-form'].puesto.value +
                "&departamento=" + document.forms['cat-form'].departamento.value.replace(/[^0-9]/g, '') +
                "&fechaalta=" + document.forms['col-form'].fechaalta.value +*/
                "&idcolaborador=" + this.colaboradorActual.idcolaborador).then(function (response) {
                // handle success
                this.app.colaboradores = response.data;
            }).catch(function (error) {
                // handle error
                alert(error);
            });
            this.opcion = 'listaColaboradores';
            this.colaboradorActual = {};
        },
        fn_editar() {
            console.log("Ejecutando función de editar tarea")
            var fd = new FormData();
            console.log("el id encargo es: " + this.tareaActual.idencargo)
            fd.append("descripcion", document.forms['edit-form'].descripcion.value);
            fd.append("fechainicio", document.forms['edit-form'].fechainicio.value);
            fd.append("fechafin", document.forms['edit-form'].fechafin.value);
            fd.append("colaborador", document.forms['edit-form'].colaborador.value);
            fd.append("status", document.forms['edit-form'].status.value);
            fd.append("responsable", document.forms['edit-form'].responsable.value);
            fd.append("idencargo", this.tareaActual.idencargo)
            axios.post('http://localhost:8080/encargo/update/?' +
                "descripcion=" + document.forms['edit-form'].descripcion.value +
                "&fechainicio=" + document.forms['edit-form'].fechainicio.value +
                "&fechafin=" + document.forms['edit-form'].fechafin.value +
                "&colaborador=" + document.forms['edit-form'].colaborador.value.replace(/[^0-9]/g, '') +
                "&status=" + document.forms['edit-form'].status.value +
                "&responsable=" + document.forms['edit-form'].responsable.value.replace(/[^0-9]/g, '') +
                "&idencargo=" + this.tareaActual.idencargo +
                "&idColaboradorActual=" + this.colaboradorLogged.idcolaborador).then(function (response) {
                // handle success
                this.app.tareas = response.data;
            }).catch(function (error) {
                // handle error
                alert(error);
            });
            this.opcion = 'listaTareas';
            this.tareaActual = {};
        },
        /**
         * Funciones de Tareas
         */
        fn_enviar: function () {
            console.log("Ejecutando función de insertar tarea")
            var fd = new FormData();
            fd.append("descripcion", document.forms['cat-form'].descripcion.value);
            fd.append("fechainicio", document.forms['cat-form'].fechainicio.value);
            fd.append("fechafin", document.forms['cat-form'].fechafin.value);
            fd.append("colaborador", document.forms['cat-form'].colaborador.value.replace(/[^0-9]/g, ''));
            fd.append("status", document.forms['cat-form'].status.value);
            fd.append("responsable", document.forms['cat-form'].responsable.value.replace(/[^0-9]/g, ''));
            fd.append("idColaboradorActual", this.colaboradorLogged.idcolaborador);
            axios({
                    method: 'post',
                    url: 'encargo/create',
                    responseType: 'json',
                    data: fd
                })
                .then(function (response) {
                    console.log("estado es: " + this.app.nuevatarea);
                    console.log(response.data);
                    this.app.tareas = response.data;

                }).catch(function (error) {
                    // handle error
                    alert(error);
                });
                this.opcion = 'listaTareas';
        },
        fn_eliminar: function (idencargo) {
            console.log("Id encargo a eliminar es: " + idencargo)
            axios.post('http://localhost:8080/encargo/delete/?idencargo=' + idencargo +
            "&idColaboradorActual=" + this.colaboradorLogged.idcolaborador)
                .then(function (response) {
                    // handle success
                    this.app.tareas = response.data;
                }).catch(function (error) {
                    // handle error
                    alert(error);
                });
        },
        fn_eliminarComentario: function (idcomentario) {
            console.log("Id encargo a eliminar es: " + idcomentario)
            axios.post('http://localhost:8080/comentario/delete/?idcomentario=' + idcomentario)
                .then(function (response) {
                    // handle success
                    this.app.comentarios = response.data;
                }).catch(function (error) {
                    // handle error
                    alert(error);
                });
        },
        fn_setTareas: function (response) {
            this.colaboradorLogged = response.data;
            console.log("LO INTENTAS???");

            axios.get('/encargo/read/?id=' + this.colaboradorLogged.idcolaborador)
                .then(function (response) {
                    this.app.tareas = response.data;
                })
            this.opcion = 'listaTareas'
        },
        fn_comments: function (id_tarea) {
            console.log("id tarea es: " + id_tarea);
            axios.get('/comentario/readPorEncargo/?id=' + id_tarea)
                .then(function (response) {
                    // handle success
                    this.app.comentarios = response.data;
                    console.log(this.app.comentarios.length)
                })
            this.opcion = 'listaComentarios'
            this.tareaActual = id_tarea;
        },
        fn_login: function () {

            userInfo = {
                usuario: document.forms['login-form'].usuario.value,
                contrasena: document.forms['login-form'].contrasena.value
            }
            axios.get('/colaborador/login', {
                params: userInfo
            }).then(this.fn_setTareas)
            .catch(function(error){
                alert(error);
            });

            if (this.colaboradorLogged != null) {
                console.log("Es distinto de null");
                id = this.colaboradorLogged.idcolaborador;
            } else {
                console.log("No es distinto de null");

            }

        },
        fn_logout() {
            this.colaboradorLogged = null;
            this.opcion = 'login';
        },
        fn_enviar_comment: function () {
            console.log("Ejecutando función de insertar comentario")
            var fd = new FormData();
            console.log("La tarea actual es: " + this.tareaActual);
            fd.append("idcolaborador", this.colaboradorLogged.idcolaborador);
            fd.append("comentario", document.forms['comment-form'].comentario.value);
            fd.append("idencargo", this.tareaActual);
            axios({
                    method: 'post',
                    url: '/comentario/create',
                    responseType: 'json',
                    data: fd
                })
                .then(function (response) {
                    console.log("Bien hecho!")
                    this.app.comentarios = response.data;
                }).catch(function (error) {
                    // handle error
                    alert(error);
                });
            this.opcion = 'listaComentarios';
        },

    }
})

/*
mounted: function () {
        
    }, //Tarea: que se actualice 
    methods: {
        
        
        
    }
*/