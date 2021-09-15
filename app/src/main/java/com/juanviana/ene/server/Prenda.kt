package com.juanviana.ene.server

import java.io.Serializable

class Prenda (
    val _id: String?= null,
    val Nombre: String?= null,
    //val Tallas: Array<Int>?= null,
    val Imagenes: String?=  null,
    val Precio: Long?= null,
        ) : Serializable
