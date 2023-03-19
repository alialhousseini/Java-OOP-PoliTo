package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_Utenti.class);
        suite.addTestSuite(TestR2_Prodotti.class);
        suite.addTestSuite(TestR3_Ticket.class);
        suite.addTestSuite(TestR4_Commenti.class);
        suite.addTestSuite(TestR5_Stats.class);
        //$JUnit-END$
        return suite;
    }

}
