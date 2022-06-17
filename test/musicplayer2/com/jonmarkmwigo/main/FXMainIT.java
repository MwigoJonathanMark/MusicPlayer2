/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer2.com.jonmarkmwigo.main;

import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author MWIGO-JON-MARK
 */
public class FXMainIT
{
    
    public FXMainIT()
    {
    }
    
    @BeforeAll
    public static void setUpClass()
    {
    }
    
    @AfterAll
    public static void tearDownClass()
    {
    }
    
    @BeforeEach
    public void setUp()
    {
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of init method, of class FXMain.
     * @throws java.lang.Exception
     */
    @Test
    public void testInit() throws Exception
    {
        System.out.println("init");
        FXMain instance = new FXMain();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class FXMain.
     */
    @Test
    public void testStart()
    {
        System.out.println("start");
        Stage stage = null;
        FXMain instance = new FXMain();
        instance.start(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class FXMain.
     */
    @Test
    public void testStop()
    {
        System.out.println("stop");
        FXMain instance = new FXMain();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class FXMain.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        FXMain.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
