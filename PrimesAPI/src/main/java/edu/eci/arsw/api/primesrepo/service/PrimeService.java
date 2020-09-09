package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.List;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public interface PrimeService
{

    public void addFoundPrime( FoundPrime foundPrime )throws PrimosException;

    public List<FoundPrime> getFoundPrimes()throws PrimosException;

    public FoundPrime getPrime( String prime )throws PrimosException;
    
    public FoundPrime getFoundPrimeId( String Number )throws PrimosException;

}
