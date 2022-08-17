package pe.com.nttdata.cliente.controller;

import java.time.LocalDate;
import java.util.Date;

public record ClienteRequest(Integer id, String nombre, String apellidoPaterno, String apellidoMaterno, String email, LocalDate fechaNacimiento) {

}
