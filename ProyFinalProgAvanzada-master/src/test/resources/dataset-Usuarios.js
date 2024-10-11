db = connect( 'mongodb://localhost:27017/proyUQ' );


db.clientes.insertMany([
    {
        _id: ObjectId('66a2a9aaa8620e3c1c5437be'),
        identificacion: "1005094005",
        nombreCompleto: "Juan Sebastian Ramos A",
        email: "juans.ramosa@uqvirtual.edu.co",
        cuenta: {
            usuario: "sebas0121",
            password: "$2a$10$0C/HSVn1Nemhn4MT89pBiODG0sCy7qYfwS94CH2A2Uo0BJN8Vcncy",
            estadoCuenta: "ACTIVA"
        },
        direccion: "B. la milagrosa m6#3",
        telefono: "3128028428",
        primerCompra: true,
        tipoUsuario: "ADMIN",
        carritoDeCompras: {
            listItemsCompra: [],
            cupon: "",
            total: 0.0
        },
        codigoValidacion: {
            codigo: "",
            fechaCreacion: {}
        },
        misTransferenciasEnviadas: [],
        misTransferenciasRecibidas: [],
        misTickets: [],
        misCupones: [],
        MisOrdenesCompra: [],
        _class: "co.edu.uniquindio.proyecto.model.Cliente"
    },{
        _id: ObjectId('66a2a9aaa8420e3c7c5497b0'),
        identificacion: "123456789",
        nombreCompleto: "Paula",
        email: "hola@gmail.com",
        cuenta: {
            usuario: "paula0121",
            password: "$2a$10$0C/HSVn1Nemhn4MT89pBiODG0sCy7qYfwS94CH2A2Uo0BJN8Vcncy",
            estadoCuenta: "ACTIVA"
        },
        direccion: "Armenia",
        telefono: "123456889",
        primerCompra: true,
        tipoUsuario: "CLIENT",
        carritoDeCompras: {
            listItemsCompra: [],
            cupon: "",
            total: 0.0
        },
        codigoValidacion: {
            codigo: "",
            fechaCreacion: {}
        },
        misTransferenciasEnviadas: [],
        misTransferenciasRecibidas: [],
        misTickets: [],
        misCupones: [],
        MisOrdenesCompra: [],
        _class: "co.edu.uniquindio.proyecto.model.Cliente"
    },{
        _id: ObjectId('66a2a9aaa8427783c7c6697b4'),
        identificacion: "123456789",
        nombreCompleto: "Sebas Murcia",
        email: "hola1@gmail.com",
        cuenta: {
            usuario: "murcia0121",
            password: "$2a$10$0C/HSVn1Nemhn4MT89pBiODG0sCy7qYfwS94CH2A2Uo0BJN8Vcncy",
            estadoCuenta: "ACTIVA"
        },
        direccion: "Armenia norte",
        telefono: "51243",
        primerCompra: true,
        tipoUsuario: "CLIENT",
        carritoDeCompras: {
            listItemsCompra: [],
            cupon: "",
            total: 0.0
        },
        codigoValidacion: {
            codigo: "",
            fechaCreacion: {
            }
        },
        misTransferenciasEnviadas: [],
        misTransferenciasRecibidas: [],
        misTickets: [],
        misCupones: [],
        MisOrdenesCompra: [],
        _class: "co.edu.uniquindio.proyecto.model.Cliente"
    }

]);
