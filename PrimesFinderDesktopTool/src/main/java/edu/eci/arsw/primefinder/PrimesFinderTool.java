package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

	public static List<PrimeThread> ListThreads;
    public static int numThreads;
    public static int inicio;
    public static int fin;
    public static int intervalos;

	public static void main(String[] args) {
		            
            int maxPrim=1000;
            numThreads = 4;
            inicio=0;
            intervalos=maxPrim/numThreads;
            PrimesResultSet prs = new PrimesResultSet("esteban");
            for (int i = 0; i < numThreads; i++) {
            	inicio=fin;
            	fin=inicio+intervalos;
                if(i==numThreads-1){
                    fin=maxPrim;
                }
                PrimeThread thread = new PrimeThread(new BigInteger(String.valueOf(inicio)), new BigInteger(String.valueOf(fin)), prs);
                ListThreads.add(thread);
            }
            
            for (PrimeThread prime:ListThreads) {
                prime.start();
            }
            for (PrimeThread prime:ListThreads) {
                prime.iniciar();
            }

            boolean yaAcabo = true;
            while (yaAcabo) {
                try {
                	yaAcabo=false;
                    Thread.sleep(10);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                        System.out.println("Se detuvo");
                        System.out.println("Prime numbers found:");
                        System.out.println(prs.getPrimes());

                    } else {
                        for (PrimeThread p:ListThreads) {
                            p.iniciar();
                        }


                    }
                    papa:
                    for (PrimeThread p:ListThreads) {
                        if (p.isAlive()){

                        	yaAcabo = true;
                            break papa;
                        }

                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Primes Found:");
            System.out.println(prs.getPrimes());

  
            
	}
	
}


