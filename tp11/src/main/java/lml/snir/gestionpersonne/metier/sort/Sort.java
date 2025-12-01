package lml.snir.gestionpersonne.metier.sort;

import java.util.Comparator;

public abstract class Sort {
    private long start;

    /**
     * sort an array of Comparable object with is internal comparator
     * @param values : data who want to be sorted
     * @return the execution time in ms
     */
    public abstract long sort(Comparable[] values);

    /**
     * sort an array of Object object with an external dedicated comparator
     * @param values : data who want to be sorted
     * @param comparator : external comparator
     * @return the execution time in ms
     */
    public abstract long sort(Object[] values, Comparator comparator);

    /**
     * start timer to compute the execution time
     */
    public void start() {
        this.start= System.currentTimeMillis();
    }

    /**
     * stop timer and compute the execution time
     * @return the execution time in ms
     */
    public long stop() {
        long stop = System.currentTimeMillis();
        long duration = stop - start;
        return duration;
    }
}
