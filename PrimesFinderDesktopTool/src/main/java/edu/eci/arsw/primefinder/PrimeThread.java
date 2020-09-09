package edu.eci.arsw.primefinder;

import java.math.BigInteger;

import edu.eci.arsw.math.MathUtilities;

public class PrimeThread extends Thread{
	
	BigInteger inicio,fin;
	PrimesResultSet prs;
	public boolean parar;
	public PrimeThread(BigInteger inicio,BigInteger fin,PrimesResultSet prs) {
		this.inicio=inicio;
        this.fin=fin;
        this.prs=prs;
        this.parar=true;
	}
	
	@Override
    public void run(){
		MathUtilities mt=new MathUtilities();
        
        int itCount=0;
    
        BigInteger i=this.inicio;
        while (i.compareTo(this.fin)<=0){
        	synchronized(this){
                while (NecesitaParar()){
                    try {
                        wait();
                    } catch (InterruptedException ignored) {

                    }
                }

            }
            itCount++;
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }

            i=i.add(BigInteger.ONE);
        }
	}
	public synchronized void iniciar() {
	        this.parar=false;
	        notifyAll();
	}
	public synchronized void pausar(){
	        this.parar=true;
	}
	public boolean NecesitaParar() {
		return parar;
	}
}
