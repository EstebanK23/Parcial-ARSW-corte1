package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public class PrimeServiceStub implements PrimeService{
	
	private List<FoundPrime> ListPrimes = new CopyOnWriteArrayList<>();
    @Override
    public void addFoundPrime( FoundPrime foundPrime )
    {
    	 for(FoundPrime found: ListPrimes){
             if(found.getPrime().equals(foundPrime.getPrime())){
            	 System.out.println("Este primo ya esta");
             }
         }
    	 ListPrimes.add(foundPrime);
         
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        //TODO
        return ListPrimes;
    }

    @Override
    public FoundPrime getPrime(String primeNumber) throws PrimosException {
        for(FoundPrime Primos: ListPrimes){
            if(Primos.getPrime().equals(primeNumber)){
                return Primos;
            }
        }
        throw new PrimosException("Nummero primo no registrado");
    }


    @Override
    public FoundPrime getFoundPrimeId(String primeNumber) throws PrimosException {
        for(FoundPrime Primos: ListPrimes){
            if(Primos.getPrime().equals(primeNumber)){
                return Primos;
            }
        }
        throw new PrimosException("Usuario no registrado");
    }
}
