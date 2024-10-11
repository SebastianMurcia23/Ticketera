db = connect( 'mongodb://localhost:27017/proyUQ' );


db.clientes.insertMany([

    {

        _id: {
            $oid: "670755fc1c4e5a456232ca233"
        },
        nombre: "Cepeda",
        direccion: "Estadio centenario",
        ciudad: "Armenia",
        descripcion: "Eres Bienvenido a nuestro nuevo evento Leyedas Vallenatas",
        tipoEvento: "CONCIERTOS",
        poster: "foto.png",
        imgBoleta: "foto2.png",
        distribucionLocalidades: "imagen.png",
        fechaEvento: {
            $date: "2024-12-10T04:20:07.905Z"
        },
        localidades: [
            {
                _id: "General",
                nombreLocalidad: "General",
                precio: 120000,
                capacidadMaxima: 40,
                cantAsientosDisponibles: 40,
                asientosLocalidad: [
                    {
                        _id: "COD-General - 1",
                        disponible: true,
                        numeroAsiento: "General - 1",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 2",
                        disponible: true,
                        numeroAsiento: "General - 2",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 3",
                        disponible: true,
                        numeroAsiento: "General - 3",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 4",
                        disponible: true,
                        numeroAsiento: "General - 4",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 5",
                        disponible: true,
                        numeroAsiento: "General - 5",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 6",
                        disponible: true,
                        numeroAsiento: "General - 6",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 7",
                        disponible: true,
                        numeroAsiento: "General - 7",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 8",
                        disponible: true,
                        numeroAsiento: "General - 8",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 9",
                        disponible: true,
                        numeroAsiento: "General - 9",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 10",
                        disponible: true,
                        numeroAsiento: "General - 10",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 11",
                        disponible: true,
                        numeroAsiento: "General - 11",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 12",
                        disponible: true,
                        numeroAsiento: "General - 12",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 13",
                        disponible: true,
                        numeroAsiento: "General - 13",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 14",
                        disponible: true,
                        numeroAsiento: "General - 14",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 15",
                        disponible: true,
                        numeroAsiento: "General - 15",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 16",
                        disponible: true,
                        numeroAsiento: "General - 16",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 17",
                        disponible: true,
                        numeroAsiento: "General - 17",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 18",
                        disponible: true,
                        numeroAsiento: "General - 18",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 19",
                        disponible: true,
                        numeroAsiento: "General - 19",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 20",
                        disponible: true,
                        numeroAsiento: "General - 20",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 21",
                        disponible: true,
                        numeroAsiento: "General - 21",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 22",
                        disponible: true,
                        numeroAsiento: "General - 22",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 23",
                        disponible: true,
                        numeroAsiento: "General - 23",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 24",
                        disponible: true,
                        numeroAsiento: "General - 24",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 25",
                        disponible: true,
                        numeroAsiento: "General - 25",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 26",
                        disponible: true,
                        numeroAsiento: "General - 26",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 27",
                        disponible: true,
                        numeroAsiento: "General - 27",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 28",
                        disponible: true,
                        numeroAsiento: "General - 28",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 29",
                        disponible: true,
                        numeroAsiento: "General - 29",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 30",
                        disponible: true,
                        numeroAsiento: "General - 30",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 31",
                        disponible: true,
                        numeroAsiento: "General - 31",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 32",
                        disponible: true,
                        numeroAsiento: "General - 32",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 33",
                        disponible: true,
                        numeroAsiento: "General - 33",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 34",
                        disponible: true,
                        numeroAsiento: "General - 34",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 35",
                        disponible: true,
                        numeroAsiento: "General - 35",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 36",
                        disponible: true,
                        numeroAsiento: "General - 36",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 37",
                        disponible: true,
                        numeroAsiento: "General - 37",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 38",
                        disponible: true,
                        numeroAsiento: "General - 38",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 39",
                        disponible: true,
                        numeroAsiento: "General - 39",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-General - 40",
                        disponible: true,
                        numeroAsiento: "General - 40",
                        usuarioCompra: "",
                        idTicket: ""
                    }
                ]
            },
            {
                _id: "Platino",
                nombreLocalidad: "Platino",
                precio: 220000,
                capacidadMaxima: 20,
                cantAsientosDisponibles: 20,
                asientosLocalidad: [
                    {
                        _id: "COD-Platino - 1",
                        disponible: true,
                        numeroAsiento: "Platino - 1",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 2",
                        disponible: true,
                        numeroAsiento: "Platino - 2",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 3",
                        disponible: true,
                        numeroAsiento: "Platino - 3",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 4",
                        disponible: true,
                        numeroAsiento: "Platino - 4",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 5",
                        disponible: true,
                        numeroAsiento: "Platino - 5",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 6",
                        disponible: true,
                        numeroAsiento: "Platino - 6",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 7",
                        disponible: true,
                        numeroAsiento: "Platino - 7",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 8",
                        disponible: true,
                        numeroAsiento: "Platino - 8",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 9",
                        disponible: true,
                        numeroAsiento: "Platino - 9",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 10",
                        disponible: true,
                        numeroAsiento: "Platino - 10",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 11",
                        disponible: true,
                        numeroAsiento: "Platino - 11",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 12",
                        disponible: true,
                        numeroAsiento: "Platino - 12",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 13",
                        disponible: true,
                        numeroAsiento: "Platino - 13",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 14",
                        disponible: true,
                        numeroAsiento: "Platino - 14",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 15",
                        disponible: true,
                        numeroAsiento: "Platino - 15",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 16",
                        disponible: true,
                        numeroAsiento: "Platino - 16",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 17",
                        disponible: true,
                        numeroAsiento: "Platino - 17",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 18",
                        disponible: true,
                        numeroAsiento: "Platino - 18",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 19",
                        disponible: true,
                        numeroAsiento: "Platino - 19",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Platino - 20",
                        disponible: true,
                        numeroAsiento: "Platino - 20",
                        usuarioCompra: "",
                        idTicket: ""
                    }
                ]
            },
            {
                _id: "Palcos",
                nombreLocalidad: "Palcos",
                precio: 320000,
                capacidadMaxima: 10,
                cantAsientosDisponibles: 10,
                asientosLocalidad: [
                    {
                        _id: "COD-Palcos - 1",
                        disponible: true,
                        numeroAsiento: "Palcos - 1",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 2",
                        disponible: true,
                        numeroAsiento: "Palcos - 2",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 3",
                        disponible: true,
                        numeroAsiento: "Palcos - 3",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 4",
                        disponible: true,
                        numeroAsiento: "Palcos - 4",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 5",
                        disponible: true,
                        numeroAsiento: "Palcos - 5",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 6",
                        disponible: true,
                        numeroAsiento: "Palcos - 6",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 7",
                        disponible: true,
                        numeroAsiento: "Palcos - 7",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 8",
                        disponible: true,
                        numeroAsiento: "Palcos - 8",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 9",
                        disponible: true,
                        numeroAsiento: "Palcos - 9",
                        usuarioCompra: "",
                        idTicket: ""
                    },
                    {
                        _id: "COD-Palcos - 10",
                        disponible: true,
                        numeroAsiento: "Palcos - 10",
                        usuarioCompra: "",
                        idTicket: ""
                    }
                ]
            }
        ],
        estadoEvento: "ACTIVO",
        _class: "co.edu.uniquindio.proyecto.model.Evento"
    }

])