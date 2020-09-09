package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import edu.eci.arsw.api.primesrepo.service.PrimosException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController 
{
	@Autowired
    PrimeService primeService;


    @RequestMapping(value = "/primes" ,method = RequestMethod.GET )
    public ResponseEntity<?> getPrimes(){
    	System.out.println("Consulta todos los primos");
        try {
        	return new ResponseEntity<>(primeService.getFoundPrimes(),HttpStatus.ACCEPTED);
        } catch(PrimosException e) {
        	return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( value = "/primes", method = RequestMethod.POST )
    public ResponseEntity<?> postPrimes(FoundPrime Primo)    {
        System.out.println("Ingresa  un numero especifico de primo");
        try {
        	primeService.addFoundPrime(Primo);
        	return new ResponseEntity<>(HttpStatus.CREATED);
        } catch(PrimosException e) {
        	return new ResponseEntity<>("Error",HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping( value = "/primes/{primenumber}", method = RequestMethod.GET )
    public ResponseEntity<?> getPrime(String primenumber){
        System.out.println("Consulta un numero especifico de primo");
        try {
        	return new ResponseEntity<>(primeService.getFoundPrimeId(primenumber),HttpStatus.ACCEPTED);
        } catch(PrimosException e) {
        	return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
        }
    } 
    //TODO implement additional methods provided by PrimeService



}
