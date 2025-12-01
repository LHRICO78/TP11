package lml.snir.gestionpersonne.metier.sort;

import java.util.Comparator;

public class ExtractionSort extends  Sort {

    @Override
    public long  sort(Comparable[] values) {
        this.start();
        Comparable min;
        int posMin = 0;
        
	for (int i = 0; i < values.length - 1; i++) {           
            // looking for min
            

            // exchange with min
            
        }

        return this.stop();
    }

    @Override
    public long  sort(Object[] values, Comparator comparator) {
        System.out.println("ExtractionSort.sort(Object[] values, Comparator comparator)");
        this.start();        
        Object min;
        int posMin = 0;
        
	for (int i = 0; i < values.length - 1; i++) {           
            // looking for min
            

            // exchange with min
            
        }
        return this.stop();
    }

}
