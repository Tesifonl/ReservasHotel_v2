package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;


public class Habitaciones {
	
	private int capacidad;
	private int tamano;
	private static Habitacion [] coleccionHabitaciones;
	
	public Habitaciones(int capacidad) {
		if(capacidad>0) {
			this.capacidad=capacidad;
			coleccionHabitaciones=new Habitacion [capacidad];
		}else {throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");}
	}
	

	public Habitacion [] get() {
		Habitacion[] copia=copiaProfundaHabitaciones();
		return copia;
	}
	
	
	private Habitacion [] copiaProfundaHabitaciones() {
		Habitacion [] copiahabitaciones=new Habitacion [coleccionHabitaciones.length];
		
		for (int i=0;i<coleccionHabitaciones.length;i++) {
		if (coleccionHabitaciones[i]!=null) {copiahabitaciones[i]=new Habitacion(coleccionHabitaciones[i]);}
		else {copiahabitaciones[i]=null;}
		}
		return copiahabitaciones;
	}

	
	public Habitacion [] get (TipoHabitacion tipoHabitacion) {
		
		if(tipoHabitacion!=null) {
			Habitacion [] nuevoArray=new Habitacion[coleccionHabitaciones.length];
			int contador=0;
			
			for (int i=0;i<coleccionHabitaciones.length;i++) {
				if(coleccionHabitaciones[i] != null && coleccionHabitaciones[i].getTipoHabitacion().equals(tipoHabitacion)) {
					nuevoArray[contador]=coleccionHabitaciones[i];
					contador++;
				}
			
			}return nuevoArray;
		}else {throw new  NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");}
	}
	
	
	public int getTamano() {
		int tamano=0;
		
		for (int i=0;i<coleccionHabitaciones.length;i++) {
		if(coleccionHabitaciones[i]!=null) {tamano++;}
		}
		return tamano;
	}
	
	public int getCapacidad() {
		return coleccionHabitaciones.length;
	}
	
	public void insertar (Habitacion habitacion) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		
		if(habitacion!=null) {
			for (int i=0;i<coleccionHabitaciones.length;i++) {
				if(coleccionHabitaciones[i] != null && coleccionHabitaciones[i].equals(habitacion)) {
					throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");}
				else {
					noEncontrado=true;}}
			
			if(noEncontrado==true && getTamano()<getCapacidad()){
				coleccionHabitaciones[getTamano()]=habitacion;}
			else{
				throw new OperationNotSupportedException("ERROR: No se aceptan m�s habitaciones.");} 
			
		}else {throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");}
	}
	
	public int buscarIndice (Habitacion habitacion) {
		if(habitacion!=null) {
			int posicion=0;
	
			for (int i=0;i<coleccionHabitaciones.length;i++) {
				if (coleccionHabitaciones[i]!=null && coleccionHabitaciones[i].equals(habitacion)) {
					posicion=i;}
			}
			return posicion;		
		}else {throw new NullPointerException("ERROR: No se puede buscar una habitacion nula");}
	}
	
	
	public boolean tamanoSuperado(int indice) {
		boolean superado=false;
		
		if (indice> getTamano()) {superado=true;}
		else{superado=false;}
		
		return superado;
	}
	
	public boolean capacidadSuperada(int indice) {
		boolean superado=false;
		
		if (indice> getCapacidad()) {superado=true;}
		else{superado=false;}
		
		return superado;
	}
	
	public Habitacion buscar(Habitacion habitacion) {	
		if(habitacion!=null) {
			boolean encontrado=false;
			int posicion = 0;
			for (int i=0;i<coleccionHabitaciones.length;i++) {
				if(coleccionHabitaciones[i]!=null 
					&& coleccionHabitaciones[i].getIdentificador().equals(habitacion.getIdentificador())) {
					encontrado=true;
					posicion = i;}
				}
				
			if (encontrado==true) {
				return new Habitacion (coleccionHabitaciones[posicion]);}else {return null;}
		}else {throw new NullPointerException("ERROR: No se puede buscar una habitacion nula");}
	}
	
	public void borrar (Habitacion habitacion) throws OperationNotSupportedException {
		boolean encontrado=false;
		
		if(habitacion!=null) {
		int contador=0;
		int indice=0;
			for (int i=0;i<coleccionHabitaciones.length;i++) {
				contador=i;
				if(coleccionHabitaciones[i]!=null && coleccionHabitaciones[i].equals(habitacion)) {
				encontrado=true;indice=contador;}}
				
			if(encontrado==true){coleccionHabitaciones[indice]=null;
			desplazarUnaPosicionHaciaIzquierda(indice);}
			else {throw new OperationNotSupportedException("ERROR: No existe ninguna habitaci�n como la indicada.");}	
		
		}else {throw new NullPointerException("ERROR: No se puede borrar una habitaci�n nula.");}
	}
	
	
	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i=indice;i<coleccionHabitaciones.length-1;i++) {
			coleccionHabitaciones[i]=coleccionHabitaciones[i+1];
			coleccionHabitaciones[coleccionHabitaciones.length-1]=null;}
			
	}
	
}


