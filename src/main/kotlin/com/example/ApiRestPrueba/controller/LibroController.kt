package com.example.ApiRestPrueba.controller

import com.example.ApiRestPrueba.model.Libro
import org.springframework.web.bind.annotation.*

@RestController
class LibroController {

    private val libros: MutableList<Libro> = mutableListOf(Libro(1L,"Patatas","Thiller","José"),
                                                        Libro(2L,"Y no quedó ninguno","Drama","Agata Chistie"),
                                                        Libro(3L,"La Biblia","Religioso","Dios")
                                                        )

    //CRUD de libros
    //metodos que hacerte  C(crear) -> Post
    //metodos que hacerte  R(LEER) -> Get
    //metodos que hacerte  U(actualizar) -> Put
    //metodos que hacerte  D(eliminar) -> Delete
    @PostMapping("/libros")
    fun insert(
        @RequestBody libroNuevo: Libro
    ): Libro? {
        val libroEnLista = libros.find { libro: Libro -> libro.id == libroNuevo.id }
      if (libroEnLista==null){
          libros.add(libroNuevo)
          return libroNuevo
      }else{
          return null
      }
    }


    @GetMapping("/libros/{id}")
    fun getById(
        @PathVariable id: String//Aqui pillamos la variable id
    ): Libro? {
        try {
            val idL  = id.toLong()

            val libro = libros.find { libro -> libro.id == idL }

            return libro

        }catch (e: Exception){
            println(e.message)
            return null
        }
    }


    @PutMapping("/libros")
    fun update(
        @RequestBody libroNuevo: Libro
    ): Libro? {

        val libroEnLista = libros.find { it.id == libroNuevo.id }

        if (libroEnLista!=null){
            libroEnLista.genero = libroNuevo.genero
            libroEnLista.titulo = libroNuevo.titulo
            libroNuevo.autor = libroNuevo.autor
            return libroNuevo
        }else{
            return null
        }

    }


    @DeleteMapping("/libros/{id}")
    fun deleteById(
        @PathVariable id: String
    ): String? {
        try {
            val idL  = id.toLong()

            val libro = libros.find { libro -> libro.id == idL }

            if (libro!=null){
                libros.remove(libro)
                return "El libro se ha eliminado con exito."
            }else{
                return "El libro no se ha encontrado."
            }

        }catch (e: Exception){
            println(e.message)
            return null
        }
    }

}