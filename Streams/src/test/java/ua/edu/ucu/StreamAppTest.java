package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }
    
    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAverage(){
        StreamApp.average (AsIntStream.of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMax(){
        StreamApp.max(AsIntStream.of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMin(){
        StreamApp.min(AsIntStream.of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySum(){
        StreamApp.sum(AsIntStream.of());
    }

    @Test
    public void testEmptyCount(){
        long expResult = 0;
        long result = StreamApp.count(AsIntStream.of());
        assertEquals(expResult, result);
    }

    @Test
    public void testAverage(){
        double expResult = (double) 42 / 9;
        double result = StreamApp.average(intStream);
        assertEquals(expResult, result, 0.00001);
    }

    @Test
    public void testMax(){
        int expResult = 10;
        int result = StreamApp.max(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testMin(){
        int expResult = 0;
        int result = StreamApp.min(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testCount(){
        long expResult = 9;
        long result = StreamApp.count(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testSum(){
        int expResult = 42;
        int result = StreamApp.sum(intStream);
        assertEquals(expResult, result);
    }
}
