package pe.com.nttdata.cliente.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.cliente.model.Cliente;
import pe.com.nttdata.cliente.service.IClienteService;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/cliente")
@AllArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        log.info("listar clientes");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable("id") Integer id) {
        log.info("obtener cliente: ", id);
        return new ResponseEntity<>(clienteService.obtenerCliente(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarCliente(@Valid @RequestBody Cliente cliente) {
        log.info("nuevo registro de cliente {}", cliente);
        Cliente newCliente = clienteService.registrarCliente(cliente);
        return new ResponseEntity<ClienteRequest>(new ClienteRequest(newCliente.getId(), cliente.getNombre(), cliente.getApellidoPaterno(), cliente.getApellidoMaterno() , cliente.getEmail(), cliente.getFechaNacimiento()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> modificarCliente(@Valid @RequestBody Cliente cliente) {
        log.info("modificar datos de cliente {}", cliente);
        clienteService.modificarCliente(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminarCliente(@PathVariable("id") Integer id) {
        log.info("eliminar cliente: ", id);
        clienteService.eliminarCliente(id);
    }
    // implemetacion de los GetMapping
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> listarClientesPorNombre(@PathVariable("nombre") String nombre) {
        List<Cliente> clientes = clienteService.listarClientesPorNombre(nombre);
        log.info("listar clientes por nombre");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/paterno/{paterno}")
    public ResponseEntity<?> listarClientesPorApellidoPaterno(@PathVariable("paterno") String paterno) {
        List<Cliente> clientes = clienteService.listarClientesPorApellidoPaterno(paterno);
        log.info("listar clientes por apellido paterno");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/materno/{materno}")
    public ResponseEntity<?> listarClientesPorApellidoMaterno(@PathVariable("materno") String materno) {
        List<Cliente> clientes = clienteService.listarClientesPorApellidoMaterno(materno);
        log.info("listar clientes por apellido materno");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    // parametros por URL
    @GetMapping(params="nombre")
    public ResponseEntity<?> listarClientesPorNombre2(@RequestParam String nombre) {
        List<Cliente> clientes = clienteService.listarClientesPorNombre(nombre);
        log.info("listar clientes por nombre");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "paterno")
    public ResponseEntity<?> listarClientesPorApellidoPaterno2(@RequestParam String paterno) {
        List<Cliente> clientes = clienteService.listarClientesPorApellidoPaterno(paterno);
        log.info("listar clientes por apellido paterno");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(params = "materno")
    public ResponseEntity<?> listarClientesPorApellidoMaterno2(@RequestParam String materno) {
        List<Cliente> clientes = clienteService.listarClientesPorApellidoMaterno(materno);
        log.info("listar clientes por apellido materno");
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping(params="fechaNacimiento")
    public ResponseEntity<?> listarClientesPorFechaNacimiento(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento) {
        List<Cliente> clientes = clienteService.listarClientesPorFechaNacimiento(fechaNacimiento);
        log.info("listar clientes por fecha de nacimiento: ", fechaNacimiento);
        return new ResponseEntity<>(clientes, clientes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
