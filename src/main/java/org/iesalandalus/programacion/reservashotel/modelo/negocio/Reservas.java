package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

public class Reservas {
	
	private int capacidad;
	private int tamano;
	private static Reserva [] coleccionReservas;
	
	public Reservas (int capacidad) {
		if(capacidad>0) {
			this.capacidad=capacidad;
			coleccionReservas=new Reserva [capacidad];
		}else {throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");}
	}
	

	public Reserva [] get() {
		Reserva[] copia=copiaProfundaReservas();
		return copia;
	}
	
	
	private Reserva [] copiaProfundaReservas() {
		Reserva [] copiaReservas=new Reserva [coleccionReservas.length];
		
		for (int i=0;i<coleccionReservas.length;i++) {
			if (coleccionReservas[i]!=null) {
				copiaReservas[i]=new Reserva(coleccionReservas[i]);}
			else {
				copiaReservas[i]=null;}
		}
		return copiaReservas;
	}

	
	
	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionReservas.length;i++) {
			if(coleccionReservas[i]!=null) {tamano++;}
		}
		return tamano;
	}
	
	public int getCapacidad() {
		return coleccionReservas.length;
	}
	
	public void insertar (Reserva reserva) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		
		if(reserva!=null) {
			for (int i=0;i<coleccionReservas.length;i++) {
				if(coleccionReservas[i] != null && coleccionReservas[i].equals(reserva)) {
					throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");}
				else {
					noEncontrado=true;}}
			
			if(noEncontrado==true && getTamano()<getCapacidad()){
				coleccionReservas[getTamano()]=reserva;}
			else{
				throw new OperationNotSupportedException("ERROR: No se aceptan m�s reservas.");} 
			
		}else {throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");}
	}
	
	public int buscarIndice (Reserva reserva) {
		if(reserva!=null) {
			int posicion=0;
	
			for (int i=0;i<coleccionReservas.length;i++) {
				if (coleccionReservas[i]!=null && coleccionReservas[i].equals(reserva)) {
					posicion=i;}
				
			}
			return posicion;		
		}else {throw new NullPointerException("ERROR: No se puede buscar una reserva nula");}
	}
	
	
	public boolean tamanoSuperado(int indice) {
		boolean superado=false;
		
		if (indice> getTamano()) {
			superado=true;}
		else{superado=false;}
		
		return superado;
	}
	
	public boolean capacidadSuperada(int indice) {
		boolean superado=false;
		
		if (indice> getCapacidad()) {superado=true;}
		else{superado=false;}
		
		return superado;
	}
	
	public Reserva buscar(Reserva reserva) {	
		
		if(reserva!=null) {
			boolean encontrado=false;
			
			for (int i=0;i<coleccionReservas.length;i++) {
				if(coleccionReservas[i]!=null && coleccionReservas[i].equals(reserva)) {
					encontrado=true;}
			}
			
			if (encontrado==true) {
				return new Reserva(reserva);}else {return null;}
		}else {throw new NullPointerException("ERROR: No se puede buscar una reserva nula");}
	}
	
	public void borrar (Reserva reserva) throws OperationNotSupportedException {
		boolean encontrado=false;
		
		if(reserva!=null) {
		int contador=0;
		int indice=0;
			for (int i=0;i<coleccionReservas.length;i++) {
				contador=i;
				if(coleccionReservas[i]!=null && coleccionReservas[i].equals(reserva)){
					encontrado=true;indice=contador;}}
			
			if(encontrado==true){
				coleccionReservas[indice]=null;	
				desplazarUnaPosicionHaciaIzquierda(indice);}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");}	
		
		}else {throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");}
	}
	
	
	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i=indice;i<coleccionReservas.length-1;i++) {
			coleccionReservas[i]=coleccionReservas[i+1];
			coleccionReservas[coleccionReservas.length-1]=null;}
			
	}
	
	public Reserva [] getReservas (Huesped huesped) {
	
	if(huesped!=null) {
		Reserva [] nuevoArray=new Reserva[coleccionReservas.length];
		boolean encontrado=false;
		int posicion=0;
	
		
		for (int i=0;i<coleccionReservas.length;i++) {
			if(coleccionReservas[i] != null && coleccionReservas[i].getHuesped().getDni().equals(huesped.getDni())) {
				encontrado=true;
				nuevoArray[posicion]=coleccionReservas[i];
				posicion++;
			}
		}	
			
		if (encontrado==false) {
			return null;
		}

		return nuevoArray;
		
	}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");}
	}
	
	public Reserva [] getReservas (TipoHabitacion tipoHabitacion) {
		
		if(tipoHabitacion!=null) {
			Reserva [] nuevoArray=new Reserva[coleccionReservas.length];
			boolean encontrado=false;
			int posicion=0;
			
			for (int i=0;i<coleccionReservas.length;i++) {
				if(coleccionReservas[i] != null && coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
				encontrado=true;
				nuevoArray[posicion]=coleccionReservas[i];
				posicion++;
				}
			}	
			if (encontrado==false) {
				return null;
			}
							
			return nuevoArray;
			
		}else {throw new  NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitaci�n nula.");}
	}
	
	
	public Reserva [] getReservasFuturas (Habitacion habitacion) {

	if(habitacion!=null) {
		Reserva [] nuevoArray=new Reserva[coleccionReservas.length];
		boolean encontrado=false;
		int posicion=0;
		
		for (int i=0;i<coleccionReservas.length;i++) 
			if(coleccionReservas[i].getHabitacion().equals(habitacion) && coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now())) {
			encontrado=true;
			nuevoArray[posicion]=coleccionReservas[i];
			posicion++;
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

