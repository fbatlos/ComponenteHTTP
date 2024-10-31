package com.example.ApiRestPrueba.controller

import com.example.ApiRestPrueba.model.Saludo
import com.example.ApiRestPrueba.model.Usuario
import org.springframework.web.bind.annotation.*

//Es un controler y las peticiones se van a buscar aquí
@RestController
class HolaMundoController {

    val credenciales = mutableMapOf<String,String>("paco" to "123","jose" to "098")

    @GetMapping("/saludo")
    fun getHolaMundo(
        @RequestParam(name = "nombre") nombre:String
    ): Saludo {
        return Saludo("Hola Mundo",nombre)
    }

    @GetMapping("/user")
    fun getCredencial(
        @RequestParam(name = "nombre") nombre:String
    ): Usuario {
        val pass = ""

        if (credenciales.containsKey(nombre)) {
            return Usuario(nombre, credenciales.get(nombre))
        }

        return Usuario(nombre,pass)
    }

    @PostMapping("/userPos")
    fun postCredencial(
        @RequestParam(name = "nombre") nombre:String,
        @RequestParam(name = "pass") pass:String
    ): Usuario {
        credenciales[nombre] = pass;
        return Usuario(nombre,pass)
    }

    @DeleteMapping("/userDel")
    fun deleteCredencial(
        @RequestParam(name = "nombre") nombre:String,
        @RequestParam(name = "pass") pass:String
    ): String {
        if (credenciales.remove(nombre)==null) {
            return "No se ha eliminado."
        }

        return "Se ha eliminado."
    }


}