package src;

import java.util.Random;
import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Main extends ApplicationFrame{
    
    public Main( String applicationTitle, String chartTitle ) {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle , "Data(n)" , "Time(seconds)" , createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 860 , 567 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.BLUE );
      renderer.setSeriesPaint( 1 , Color.RED );
      renderer.setSeriesPaint( 2 , Color.GREEN );
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }

    static double[] time_taken_merge;
    static double[] time_taken_radix;
    static double[] time_taken_quick;
    
    private XYDataset createDataset() {
        final XYSeries RadixSearch = new XYSeries( "Radix" ); 
      
      for(int i = 0; i < 200; i++){
          RadixSearch.add(i, time_taken_radix[i]);
      }                  
      
      final XYSeries ternarySearch = new XYSeries( "Merge" );   
      
      for(int i = 0; i < 200; i++){
          ternarySearch.add(i, time_taken_merge[i]);
      } 
      
      final XYSeries QuickSearch = new XYSeries( "Quick" );   
      
      for(int i = 0; i < 200; i++){
          QuickSearch.add(i, time_taken_quick[i]);
      } 
      
      final XYSeriesCollection dataset = new XYSeriesCollection( );  
      dataset.addSeries( RadixSearch );         
      dataset.addSeries( ternarySearch );
      dataset.addSeries( QuickSearch );
      return dataset;
   }
    
    /* A utility function to print array of size n*/
    public static void printArray(int arr[]){
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    public static void printArray2(double arr[]){
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    /* A method to copy contents of array A into array B*/
    public static void copyArray(int ma[], int ta[]){
        int n = ta.length;
        for (int i=0; i<n; ++i)
            ta[i] = ma[i];
        System.out.println();
    }
    
    public static void main(String[] args){
        time_taken_radix = new double[200];
        time_taken_merge = new double[200];
        time_taken_quick = new double[200];
        
        Random r = new Random();
        Algorithms algo = new Algorithms(); 
         int[] array = new int[1000];
         
         for(int i = 0; i < 1000; i++){
             array[i] = r.nextInt(((i+1)*10));
         }
         
         for(int i = 5; i <= 1000; i+=5){
             int[] temp1 = new int[i];
             int[] temp2 = new int[i];
             int[] temp3 = new int[i];
             
             copyArray(array, temp1);
             printArray(temp1);
             time_taken_merge[(i/5)-1] = algo.mergeSort(temp1, 0, (i-1));
             printArray(temp1);
             
             copyArray(array, temp2);
             printArray(temp2);
             time_taken_radix[(i/5)-1] = algo.radixSort(temp2, (i-1));
             printArray(temp2);
             
             copyArray(array, temp3);
             printArray(temp3);
             time_taken_quick[(i/5)-1] = algo.quickSort(temp3, 0, (i-1));
             printArray(temp3);
             
         }
        Main chart = new Main ("DANIEL SOFTWARE", "COMPARITIVE ANALYSIS OF SORT ALGORITHMS USING TIME COMPLEXITY");
        chart.pack( );          
        RefineryUtilities.centerFrameOnScreen( chart );          
        chart.setVisible( true ); 
    }
}
