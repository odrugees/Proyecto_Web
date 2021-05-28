package com.edu.poli.proyecto.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.sound.midi.Patch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.poli.proyecto.model.Categoria;
import com.edu.poli.proyecto.model.Comentario;
import com.edu.poli.proyecto.model.Publicacion;
import com.edu.poli.proyecto.model.Usuario;
import com.edu.poli.proyecto.repository.CategoriaRepository;
import com.edu.poli.proyecto.repository.PublicacionRepository;
import com.edu.poli.proyecto.repository.UsuarioRepository;


@RestController
@RequestMapping("/api/publicacion/")
@CrossOrigin(origins = "*")
public class PublicacionController {

	@Autowired
	PublicacionRepository publicacionRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/listar")
	public List<Publicacion> listaCategoria() {
		return publicacionRepository.findAll(Sort.by(Sort.Direction.DESC, "publicacionFecha"));
	}

	@GetMapping("/visualizar/{id}")
	public Publicacion verPublicacion(@RequestBody Integer id) {
		Publicacion publicacion = publicacionRepository.findById(id).get();

		return publicacion;
	}

	@PostMapping("/crear")
	public Publicacion crearPubicacion(@RequestBody Publicacion publicacion) {
		return publicacionRepository.save(publicacion);
	}

	@PutMapping("/modificar/{id}")
	public Publicacion modificarCategoria(@PathVariable Integer id, @RequestBody Publicacion nuevaPublicacion) {
		Publicacion dbCPublicacion = publicacionRepository.findById(id).get();

		dbCPublicacion.setPublicacionTexto(nuevaPublicacion.getPublicacionTexto());
		publicacionRepository.save(dbCPublicacion);
		return dbCPublicacion;
	}

	@DeleteMapping("eliminar/{id}")
	public Publicacion eliminarCategoria(@PathVariable Integer id) {
		Publicacion dbPublicacion = publicacionRepository.findById(id).get();
		publicacionRepository.deleteById(id);
		return dbPublicacion;
	}

	@PutMapping("/pubicacionCategoria/{idPublicacion}/{idCategoria}")
	public Publicacion asociarpublicacionCategoria(@PathVariable Integer idPublicacion,
			@PathVariable Integer idCategoria) {

		Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
		Categoria categoria = categoriaRepository.findById(idCategoria).get();

		publicacion.setCategoria(categoria);
		categoria.getPublicacion().add(publicacion);

		publicacionRepository.save(publicacion);
		categoriaRepository.save(categoria);

		return publicacion;
	}

	@PutMapping("/pubicacionUsuario/{idPublicacion}/{idUsuario}")
	public Publicacion asociarpublicacionUsuario(@PathVariable Integer idPublicacion, @PathVariable Integer idUsuario) {

		Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
		Usuario usuario = usuarioRepository.findById(idUsuario).get();

		publicacion.setUsuario(usuario);
		usuario.getPublicacion().add(publicacion);

		publicacionRepository.save(publicacion);
		usuarioRepository.save(usuario);

		return publicacion;
	}

	@PutMapping("/pubicacionMeGusta/{idPublicacion}")
	public Publicacion asociarpublicacionMeGusta(@PathVariable Integer idPublicacion) {

		Publicacion dbPublicacion = publicacionRepository.findById(idPublicacion).get();
		Integer numeroMegusta = dbPublicacion.getPublicacionMeGusta();
		numeroMegusta = numeroMegusta + 1;
		dbPublicacion.setPublicacionMeGusta(numeroMegusta);
		publicacionRepository.save(dbPublicacion);
		return dbPublicacion;
	}

	@PutMapping("/pubicacionNoMeGusta/{idPublicacion}")
	public Publicacion asociarpublicacionNoMeGusta(@PathVariable Integer idPublicacion) {

		Publicacion dbPublicacion = publicacionRepository.findById(idPublicacion).get();
		Integer numeroNoMegusta = dbPublicacion.getPublicacionNoMeGusta();
		numeroNoMegusta += 1;
		dbPublicacion.setPublicacionNoMeGusta(numeroNoMegusta);
		publicacionRepository.save(dbPublicacion);
		return dbPublicacion;
	}

	@GetMapping("/pubicacionComentarios/{idPublicacion}")
	public Set<Comentario> publicacionComentarios(@PathVariable Integer idPublicacion) {

		Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
		Set<Comentario> listaComentario = publicacion.getComentario();

		return listaComentario;
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("publicacionId") Long publicacionId) {
		Map<String, Object> response = new HashMap<>();

		Publicacion publicacion = publicacionRepository.findById(publicacionId.intValue()).get();
		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("archivos").resolve(nombreArchivo).toAbsolutePath();
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (Exception e) {
				response.put("mensaje", "Error al subir la imagen");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreArchivoAnterior = publicacion.getPublicacionImagen();

			if (nombreArchivoAnterior != null && nombreArchivoAnterior.length() > 0) {
				Path rutaArchivoAnterior = Paths.get("archivos").resolve(nombreArchivoAnterior).toAbsolutePath();
				File ArchivoAnterior = rutaArchivoAnterior.toFile();
				if (ArchivoAnterior.exists() && ArchivoAnterior.canRead()) {
					ArchivoAnterior.delete();
				}
			}
			publicacion.setPublicacionImagen(nombreArchivo);
			publicacionRepository.save(publicacion);

			response.put("publicacion", publicacion);
			response.put("mensaje", "Se subio correctamente la imagen");

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	@GetMapping("/uploads/imagen/{nombreArchivo:.+}")
	public ResponseEntity<Resource> verArchivo(@PathVariable String nombreArchivo){
		Path rutaArchivo = Paths.get("archivos").resolve(nombreArchivo).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			recurso = (Resource) new UrlResource(rutaArchivo.toUri());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen");	
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
		
	}
	{
		
	}

}
