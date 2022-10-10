package practical1;

import java.io.StringReader;
import java.util.Arrays;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.opencsv.CSVReader;
import com.sampullara.cli.Args;
import com.sampullara.cli.Argument;

public class NumberTools {

	// the cli parser library lets us make instance variables with annotations like this
	// that specify command line arguments for the program
    @Argument(alias = "m", description = "Statistic to compute for the values", required = false)
    protected String statistic = "mean";
    
    @Argument(alias = "v", description = "Comma separated (no spaces) list of values", required = true)
    protected String values = "1,2,3";
	
	public static void main(String[] args) {
	    NumberTools numberTools = new NumberTools();
	    
	    // this will parse the list of arguments passed into the program, and
	    // populate the appropriate instance variables
	    // if the required arguments were not found, it will gracefully exit 
	    Args.parseOrExit(numberTools, args);
	    
	    // turn the string of numbers into an array of doubles
	    double[] doubleValues = parseString(numberTools.values);
	    
	    // finally, compute and output the required statistic
	    System.out.println("You requested the " + numberTools.statistic + " of the list " + Arrays.toString(doubleValues));
	    System.out.println("It is: " + computeStatistic(numberTools.statistic, doubleValues));
	    
	}

	/**
	 * take a comma separated string and parse it into an array of doubles
	 * uses the openCSV library
	 */
	public static double[] parseString(String s) {
    	CSVReader reader = new CSVReader(new StringReader(s));
    	
    	double[] parsed = new double[0];
    	try {
    		String[] tmp = reader.readNext();
    		parsed = new double[tmp.length];
    		
    		for (int i = 0; i < tmp.length; i++) {
    			parsed[i] = Double.parseDouble(tmp[i]);
    		}
    		
    		reader.close();
    	} catch(Exception e) {
    		System.err.println("Error parsing string: " + s);
    		System.exit(1);
    	}
    	
    	return parsed;
    }
	
	/**
	 * compute the appropriate statistic for the given array of doubles
	 * uses the apache commons math library 
	 */
	public static double computeStatistic(String statistic, double[] values) {
		DescriptiveStatistics stats = new DescriptiveStatistics(values);
		
		if (statistic.equalsIgnoreCase("mean")) {
			return stats.getMean();
		} else if (statistic.equalsIgnoreCase("sd")) {
			return stats.getStandardDeviation();
		} else if (statistic.equalsIgnoreCase("sum")) {
			return stats.getSum();
		} else {
			System.err.println("Unknown option: " + statistic);
    		System.exit(1);
		}
		
		// should never get here
		return 0;
	}

}
