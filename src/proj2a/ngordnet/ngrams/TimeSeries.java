package proj2a.ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.*;

/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
        this.clear();
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     *  inclusive of both end points. */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int i = startYear; i <= endYear; i++) {
            Double yearData = ts.get(i);
            if (yearData != null) {
                this.put(i, yearData);
            }
        }
    }

    /** Returns all years for this TimeSeries (in any order). */
    public List<Integer> years() {
        List<Integer> yearsList = new ArrayList();
        for (Integer i: this.keySet()) {
            yearsList.add(i);
        }
        return yearsList;
    }

    /** Returns all data for this TimeSeries (in any order).
     *  Must be in the same order as years(). */
    public List<Double> data() {
        List<Double> dataList = new ArrayList<>();
        for (Double i: this.values()) {
            dataList.add(i);
        }
        return dataList;
    }

    /** Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     *  each year, sum the data from this TimeSeries with the data from TS. Should return a
     *  new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries returnTS = new TimeSeries();
        Set<Integer> allYears = new TreeSet<>();
        allYears.addAll(this.years());
        allYears.addAll(ts.years());
        for (int year: allYears) {
            Double thisYearData = this.get(year);
            Double otherYearData = ts.get(year);
            if (thisYearData == null) {
                returnTS.put(year, otherYearData);
            } else if (otherYearData == null) {
                returnTS.put(year, thisYearData);
            } else {
                returnTS.put(year, thisYearData + otherYearData);
            }
        };
        return returnTS;
    }

     /** Returns the quotient of the value for each year this TimeSeries divided by the
      *  value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
      *  throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
      *  Should return a new TimeSeries (does not modify this TimeSeries). */
     public TimeSeries dividedBy(TimeSeries ts) {
         TimeSeries newTS = new TimeSeries();
         List<Integer> thisYears = this.years();
         for (int i: thisYears) {
             Double thisData = this.get(i);
             Double tsData = ts.get(i);
             if (tsData == null) {
                 throw new IllegalArgumentException(String.format("Missing Year %2d", i));
             }
             newTS.put(i, thisData/tsData);
         }

        return newTS;
    }
}
