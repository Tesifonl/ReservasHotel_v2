package org.iesalandalus.programacion.reservashotel.modelo.negocio;


import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

public class Huespedes {
	
	private int capacidad;
	private int tamano;
	private static Huesped [] coleccionHuespedes;
	
	
	public Huespedes(int capacidad) {
		if(capacidad>0) {
			this.capacidad=capacidad;
			coleccionHuespedes=new Huesped [capacidad];
		}else {throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");}
	}
	
	
	public Huesped [] get() {
		Huesped [] copia=copiaProfundaHuespedes();
		return copia;
		
	}
	
	
	private Huesped [] copiaProfundaHuespedes() {
		Huesped [] copiahuespedes=new Huesped [coleccionHuespedes.length];
		
		for (int i=0;i<coleccionHuespedes.length;i++) {
			if(coleccionHuespedes[i]!=null) {
				copiahuespedes[i]=new Huesped(coleccionHuespedes[i]);}
			else {
				copiahuespedes[i]=null;}
		}
		return copiahuespedes;
	}

	public int getTamano () {
		int tamano=0;
		
		for (int i=0;i<coleccionHuespedes.length;i++) {
			if(coleccionHuespedes[i]!=null) {
				tamano++;}}
		return tamano;
	}
	
	public int getCapacidad () {
		return coleccionHuespedes.length;
	}
	
	/*huespedes[i] != null && huespedes[i].equals(objetoComparado)*/
			
	public void insertar (Huesped huesped) throws OperationNotSupportedException {
		boolean noEncontrado=false;
		
		if(huesped!=null) {
			for (int i=0;i<coleccionHuespedes.length;i++) 
				if(coleccionHuespedes[i] != null && coleccionHuespedes[i].getDni().equals(huesped.getDni())) {
					throw new OperationNotSupportedException("ERROR: Ya existe un hu�sped con ese dni.");}
				else {
					noEncontrado=true;}
					
			if(noEncontrado==true && getTamano()<getCapacidad()) {
				coleccionHuespedes[getTamano()]=huesped;}
			else {
				throw new OperationNotSupportedException("ERROR: No se aceptan m�s hu�spedes.");}
			
		}else {throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");}
	}
	
	public int buscarIndice (Huesped huesped) {
		if(huesped!=null) {
			int posicion=0;
	
			for (int i=0;i<coleccionHuespedes.length;i++) {	
				if (coleccionHuespedes[i] != null && coleccionHuespedes[i].equals(huesped)) {
					posicion=i;}
			}
			return posicion;		
		}else {throw new NullPointerException("ERROR: No se puede buscar un huesped nulo");}
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
	
	
	public Huesped buscar(Huesped huesped) {	
		int posicion = 0;
		if(huesped!=null) {
			boolean encontrado=false;
			
			for (int i=0;i<coleccionHuespedes.length;i++) {
				if(coleccionHuespedes[i] != null && coleccionHuespedes[i].getDni().equals(huesped.getDni())) {
					posicion = i;
					encontrado=true;}
			}
			
			if (encontrado==true) {return new Huesped(coleccionHuespedes[posicion]);}else {return null;}
		}else {throw new NullPointerException("ERROR: No se puede buscar un huesped nulo");}
	}
		
	public void borrar (Huesped huesped) throws OperationNotSupportedException  {
		boolean encontrado=false;
		
		if(huesped!=null) {
		int contador=0;
		int indice=0;
			for (int i=0;i<coleccionHuespedes.length;i++) {
				contador=i;
				if(coleccionHuespedes[i] != null && coleccionHuespedes[i].equals(huesped)) {
				encontrado=true;indice=contador;}}	
			
			if (encontrado==true) {
				coleccionHuespedes[indice] = null;
				desplazarUnaPosicionHaciaIzquierda(indice);}
			else {
				throw new OperationNotSupportedException("ERROR: No existe ning�n hu�sped como el indicado.");}
		
		}else {throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");}
	}
	

	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i=indice;i<coleccionHuespedes.length-1;i++) {
			coleccionHuespedes[i]=coleccionHuespedes[i+1];
			coleccionHuespedes [coleccionHuespedes.length-1]=null;}
			
	}
	
	
}
