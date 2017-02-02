package sales.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

import sales.ISaleStatistics;
import sales.Sale;
import sales.SaleStats;


public class TestSuite {
    
    // The seed value for the random generator
    private final long seed = 1234;
    // The number of samples -> the number of Sale objects in the list
    private final int samples = 30;
    
    //****************************************************************
    //********* INPUT vars: Used as input during the tests ***********
    //****************************************************************
    // The tested statistics interface
    private final ISaleStatistics INPUT_Stats = new SaleStats();        
    // The list of sales that should be processed in the tests
    private final List<Sale<?>> INPUT_Sales = CarSalesExample.generateSamples(samples, seed);
    // The month for testing the month filter
    private final Month INPUT_Month = CarSalesExample.sampleRandomMonth(seed, INPUT_Sales);
    // The start and end dates for testing the time period filter
    private final LocalDate[] tmpDates = CarSalesExample.sampleRandomDates(seed, INPUT_Sales);
    private final LocalDate INPUT_StartDate = tmpDates[0];
    private final LocalDate INPUT_EndDate = tmpDates[1];

    //****************************************************************
    //********* CORRECT_OUTPUT variables: Used for comparison ********
    //****************************************************************
    // The correct month filter result
    private Set<Sale<?>> CORRECT_OUTPUT_SalesFilteredByMonth;
    // The correct time period filter result
    private Set<Sale<?>> CORRECT_OUTPUT_SalesFilteredByTimePeriod;
    // The correct sorted by date result
    private List<Sale<?>> CORRECT_OUTPUT_SalesSortedByDate;
    // The correct total, average and standard deviation values
    private double CORRECT_OUTPUT_Total;
    private double CORRECT_OUTPUT_Average;
    private double CORRECT_OUTPUT_StdDev;

    /*
     * Initializes tests. DO NOT CHANGE! 
     */
    @Before
    public void initTests(){        
        //*********************************************************
        //****************** Prepare filter test ******************
        //*********************************************************
        CORRECT_OUTPUT_SalesFilteredByMonth = new HashSet<>();
        CORRECT_OUTPUT_SalesFilteredByTimePeriod = new HashSet<>();
        for (Sale<?> s : INPUT_Sales){
            if (s.getDate().getMonth() == INPUT_Month){
                CORRECT_OUTPUT_SalesFilteredByMonth.add(s);
            }        
            if (!s.getDate().isBefore(INPUT_StartDate) && !s.getDate().isAfter(INPUT_EndDate)){
                CORRECT_OUTPUT_SalesFilteredByTimePeriod.add(s);
            }
        }
            
        //*********************************************************
        //****************** Prepare sorting test *****************
        //*********************************************************
        CORRECT_OUTPUT_SalesSortedByDate = new ArrayList<>(INPUT_Sales);
        Collections.sort(CORRECT_OUTPUT_SalesSortedByDate, new Comparator<Sale<?>>() {
            @Override
            public int compare(Sale<?> o1, Sale<?> o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        
        //*********************************************************
        //**************** Prepare statistics test ****************
        //*********************************************************
        CORRECT_OUTPUT_Total = 0;    
        CORRECT_OUTPUT_Average = 0;
        CORRECT_OUTPUT_StdDev = 0;
        
        // Calc total
        for (Sale<?> s : INPUT_Sales){
            CORRECT_OUTPUT_Total += s.getPrice();
        }
        // Calc avg
        CORRECT_OUTPUT_Average = CORRECT_OUTPUT_Total / INPUT_Sales.size();

        // Calc standard deviation
        for (Sale<?> s : INPUT_Sales){
            CORRECT_OUTPUT_StdDev += (s.getPrice() - CORRECT_OUTPUT_Average) * (s.getPrice() - CORRECT_OUTPUT_Average);
        }
        CORRECT_OUTPUT_StdDev /= INPUT_Sales.size();
        CORRECT_OUTPUT_StdDev = Math.sqrt(CORRECT_OUTPUT_StdDev);
    }
    
    /*
     * TODO Implement test for -> ISaleStatistics::getFilteredSalesAsSet using 
     * ISaleStatistics::getMonthFilter
     * Input: 
     *    - INPUT_Stats: The tested statistics interface
     *    - INPUT_Sales: The list of sales that should be processed in the test
     *    - INPUT_Month: The month for testing the month filter
     * Test:
     *    - Check against CORRECT_OUTPUT_SalesFilteredByMonth
     */
    @Test
    public void testGetFilteredSalesAsSet_Month(){
        Set<Sale<?>> output = INPUT_Stats.getFilteredSalesAsSet(INPUT_Sales, INPUT_Stats.getMonthFilter(INPUT_Month));
        assertArrayEquals("Test for month filtering failed!", CORRECT_OUTPUT_SalesFilteredByMonth.toArray(), output.toArray());
    }
    
    /*
     * TODO Implement test for -> ISaleStatistics::getFilteredSalesAsSet using
     * ISaleStatistics::getTimePeriodFilter
     * Input: 
     *    - INPUT_Stats: The tested statistics interface
     *    - INPUT_Sales: The list of sales that should be processed in the test
     *    - INPUT_StartDate: The start date for testing the time period filter
     *    - INPUT_EndDate: The end date for testing the time period filter
     * Test:
     *    - Check against CORRECT_OUTPUT_SalesFilteredByTimePeriod
     */
    @Test
    public void testGetFilteredSalesAsSet_TimePeriod(){
    	Set<Sale<?>> output = INPUT_Stats.getFilteredSalesAsSet(INPUT_Sales, INPUT_Stats.getTimePeriodFilter(INPUT_StartDate, INPUT_EndDate));
    	assertArrayEquals("Test for time period filtering failed!", CORRECT_OUTPUT_SalesFilteredByTimePeriod.toArray(), output.toArray());
    }
    
    /*
     * TODO Implement test for-> ISaleStatistics::getSortedSalesList using the natural 
     * order of the sale dates
     * Input: 
     *    - INPUT_Stats: The tested statistics interface
     *    - INPUT_Sales: The list of sales that should be processed in the test 
     * Test:
     *    - Check against CORRECT_OUTPUT_SalesSortedByDate
     */
    @Test
    public void testGetSortedSalesList(){
        List<Sale<?>> output = INPUT_Stats.getSortedSalesList(INPUT_Sales, (a, b) -> (a.getDate().compareTo(b.getDate())));
        assertArrayEquals("Test for sorting by date failed!",
        		CORRECT_OUTPUT_SalesSortedByDate.toArray(), output.toArray());
        //If both arrays are equal the list was successfully sorted.
    }
    
    /*
     * TODO Implement test for -> ISaleStatistics::getTotalRevenue
     * Input: 
     *    - INPUT_Stats: The tested statistics interface 
     *    - INPUT_Sales: The list of sales that should be processed in the test 
     * Test:
     *    - Check against CORRECT_OUTPUT_Total
     */
    @Test
    public void testCalculateTotal(){
        assertTrue("Test for calculating total failed!",
        		INPUT_Stats.getTotalRevenue(INPUT_Sales) == CORRECT_OUTPUT_Total);
        //Compare totals
    }
    
    /*
     * TODO Implement test for -> ISaleStatistics::getAverageRevenue
     * Input: 
     *    - INPUT_Stats: The tested statistics interface
     *    - INPUT_Sales: The list of sales that should be processed in the test
     * Test:
     *    - Check against CORRECT_OUTPUT_Average
     */
    @Test
    public void testCalculateAverage(){
    	assertTrue("Test for calculating average failed!",
        		INPUT_Stats.getAverageRevenue(INPUT_Sales) == CORRECT_OUTPUT_Average);
        //Compare averages
    }
    
    /*
     * TODO Implement test for -> ISaleStatistics::getStdDev
     * Input: 
     *    - INPUT_Stats: The tested statistics interface 
     *    - INPUT_Sales: The list of sales that should be processed in the test
     * Test:
     *    - Check against CORRECT_OUTPUT_StdDev
     */
    @Test
    public void testCalculateStandardDeviation(){
    	System.out.println(INPUT_Stats.getStdDev(INPUT_Sales) + " | " + CORRECT_OUTPUT_StdDev);
    	assertEquals("Test for calculating standard deviation failed!",
        		INPUT_Stats.getStdDev(INPUT_Sales), CORRECT_OUTPUT_StdDev, 0.0000000001);
        //Compare standard deviations
    }
}
