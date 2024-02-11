package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

public class Reservas {

	private static ArrayList<Reserva> coleccionReservas;
	
	public Reservas () {
		
		coleccionReservas=new ArrayList<Reserva>();
	}
	

	public ArrayList<Reserva> get() {
		ArrayList<Reserva> copia=copiaProfundaReservas();
		return copia;
	}
	
	public int getTamano() {
		
		int tamano=0;
		ArrayList<Reserva> copia=copiaProfundaReservas();
		return tamano=copia.size();
	}
	
	
	private ArrayList<Reserva> copiaProfundaReservas() {
		ArrayList<Reserva> copiaReservas=new ArrayList<Reserva>();
		
		for (int i=0;i<coleccionReservas.size();i++) {
			copiaReservas.add(new Reserva(coleccionReservas.get(i)));
		}
		
		return copiaReservas;
	}

	public void insertar (Reserva reserva) throws OperationNotSupportedException {
		boolean encontrado=false;
		
		if(reserva!=null) {
			for (int i=0;i<coleccionReservas.size();i++) {
				if(coleccionReservas.get(i).equals(reserva)) {
					encontrado = true;
					throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
				}	
			}
			
			if(encontrado == false){
				coleccionReservas.add(reserva);
			}
			else{
				throw new OperationNotSupportedException("ERROR: No se aceptan m�s reservas.");} 
			
		}else {throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");}
	}

	public Reserva buscar(Reserva reserva) {	
		
		if(reserva!=null) {
			boolean encontrado=false;
			
			for (int i=0;i<coleccionReservas.size();i++) {
				if(coleccionReservas.get(i).equals(reserva)) {
					encontrado=true;
				}
			}
			
			if (encontrado==true) {
				return new Reserva(reserva);}else {return null;}
		}else {throw new NullPointerException("ERROR: No se puede buscar una reserva nula");}
	}
	
	public void borrar (Reserva reserva) throws OperationNotSupportedException {
		boolean encontrado=false;
		
		if(reserva!=null) {
		int indice=0;
			for (int i=0;i<coleccionReservas.size();i++) {
				if(coleccionReservas.get(i).equals(reserva)){
					encontrado=true;
					indice=i;
				}
			}
			
			if(encontrado==true){
				coleccionReservas.remove(indice);
			}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");}	
		
		}else {throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");}
	}

	public ArrayList<Reserva> getReservas (Huesped huesped) {
	
	if(huesped!=null) {
		ArrayList<Reserva> nuevoArray=new ArrayList<Reserva>();
		boolean encontrado=false;

		for (int i=0;i<coleccionReservas.size();i++) {
			if(coleccionReservas.get(i).getHuesped().getDni().equals(huesped.getDni())) {
				encontrado=true;
				nuevoArray.add(coleccionReservas.get(i));
			}
		}	
			
		if (encontrado==false) {
			return null;
		}

		return nuevoArray;
		
	}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");}
	}
	
	public ArrayList<Reserva>  getReservas (TipoHabitacion tipoHabitacion) {
		
		if(tipoHabitacion!=null) {
			ArrayList<Reserva> nuevoArray=new ArrayList<Reserva>();
			boolean encontrado=false;

			for (int i=0;i<coleccionReservas.size();i++) {
				if(coleccionReservas.get(i).getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
					encontrado=true;
					nuevoArray.add(coleccionReservas.get(i));
				}
			}
			
			if (encontrado==false) {
				return null;
			}
							
			return nuevoArray;
			
		}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitaci�n nula.");}
	}
	
	
	public ArrayList<Reserva> getReservasFuturas (Habitacion habitacion) {

	if(habitacion!=null) {
		ArrayList<Reserva>  nuevoArray=new ArrayList<Reserva>();
		boolean encontrado=false;
		int posicion=0;
		
		for (int i=0;i<coleccionReservas.size();i++) 
			if(coleccionReservas.get(i).getHabitacion().equals(habitacion) 
					&& coleccionReservas.get(i).getFechaInicioReserva().isAfter(LocalDate.now())) {
			encontrado=true;
			nuevoArray.add(coleccionReservas.get(i));
		}
			
		if (encontrado==true) {
			return null;
		}
		return nuevoArray;

	}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de una habitaci�n nula.");}
	}

	public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
		if(reserva==null || fecha==null) {
			throw new  NullPointerException("ERROR: No se puede hacer checkin de una reserva nula o sin fecha");
		}else {reserva.setCheckIn(fecha);}
	}
	
	public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
		if(reserva==null || fecha==null) {
			throw new  NullPointerException("ERROR: No se puede hacer checkin de una reserva nula o sin fecha");
		}else {reserva.setCheckOut(fecha);}
	}
	
	
}

