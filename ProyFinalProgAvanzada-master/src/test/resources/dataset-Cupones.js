db = connect( 'mongodb://localhost:27017/proyUQ' );

db.cupones.insertMany([
    {
        _id: {
            $oid: "6706f234c7289447ad95bf0b"
        },
        codigoUsable: "S3B4ST14N",
        fechaVencimiento: {
            "$date": "2024-11-09T21:14:27.820Z"
        },
        usosPermitidos: 1,
        usosRealizados: 0,
        estadoCupon: "DISPONIBLE",
        porcentajeCupon: 0.2,
        descripcion: "20% de descuento",
        _class: "co.edu.uniquindio.proyecto.model.Cupon"
    },
    {
        _id: {
            $oid: "6706fca5592e6e09306db760"
        },
        codigoUsable: "P4UL4",
        fechaVencimiento: {
            $date: "2024-12-09T22:03:35.500Z"
        },
        usosPermitidos: 1,
        usosRealizados: 0,
        estadoCupon: "NO_DISPONIBLE",
        porcentajeCupon: 0.25,
        descripcion: "CUPON EDITADO ",
        _class: "co.edu.uniquindio.proyecto.model.Cupon"
    },
    {
        _id: {
            $oid: "6706fca59992e6e0935db760"
        },
        codigoUsable: "MURCIA",
        fechaVencimiento: {
            $date: "2024-12-09T22:03:35.500Z"
        },
        usosPermitidos: 1,
        usosRealizados: 0,
        estadoCupon: "NO_DISPONIBLE",
        porcentajeCupon: 0.25,
        descripcion: "CUPON EDITADO ",
        _class: "co.edu.uniquindio.proyecto.model.Cupon"
    },
    {
        _id: {
            $oid: "6704fca59942e6e0935db764"
        },
        codigoUsable: "HOLA",
        fechaVencimiento: {
            $date: "2024-12-09T22:03:35.500Z"
        },
        usosPermitidos: 1,
        usosRealizados: 0,
        estadoCupon: "NO_DISPONIBLE",
        porcentajeCupon: 0.20,
        descripcion: "CUPON EDITADO ",
        _class: "co.edu.uniquindio.proyecto.model.Cupon"
    },
    {
        _id: {
            $oid: "6704fva599u2e6t093rdb764"
        },
        codigoUsable: "HOLA2",
        fechaVencimiento: {
            $date: "2024-12-09T22:03:35.500Z"
        },
        usosPermitidos: 1,
        usosRealizados: 0,
        estadoCupon: "DISPONIBLE",
        porcentajeCupon: 0.10,
        descripcion: "CUPON EDITADO ",
        _class: "co.edu.uniquindio.proyecto.model.Cupon"
    }

])