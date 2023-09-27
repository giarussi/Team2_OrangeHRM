package dashboardTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import base.Base;
import pages.DashboardPage;


public class DashboardTest extends Base {
    private DashboardPage dashboard;

    @BeforeSuite
    public void setUpDashboard() {
        // login method that lands  on the dashboard
    	dashboard = loginpage.login("admin", "admin123");
        
    }

    @Test
    public void testAdminLinkPresence() {
        Assert.assertTrue(dashboard.isAdminLinkPresent(), "Admin link is not present on the dashboard.");    
    }

    @Test
    public void testRecruitmentLinkPresence() {
        Assert.assertTrue(dashboard.isRecruitmentLinkPresent(), "Recruitment link is not present on the dashboard.");
    }

    @Test
    public void testMyInfoLinkPresence() {
        Assert.assertTrue(dashboard.isMyInfoLinkPresent(), "My Info link is not present on the dashboard.");
    }

    @Test
    public void testPerformanceLinkPresence() {
        Assert.assertTrue(dashboard.isPerformanceLinkPresent(), "Performance link is not present on the dashboard.");
    }

    @Test
    public void testDirectoryLinkPresence() {
        Assert.assertTrue(dashboard.isDirectoryLinkPresent(), "Directory link is not present on the dashboard.");
    }

    @Test
    public void testMaintenanceLinkPresence() {
        Assert.assertTrue(dashboard.isMaintenanceLinkPresent(), "Maintenance link is not present on the dashboard.");
    }

    @Test
    public void testClaimLinkPresence() {
        Assert.assertTrue(dashboard.isClaimLinkPresent(), "Claim link is not present on the dashboard.");
    }
    
    @Test
    public void testTimeatWork() {
    	dashboard.clickProfile();
    	
    	Assert.assertEquals(dashboard.getTimeAtWorkTitle(), "Time at Work", "Widget title is incorrect.");
    }
    
//    @AfterMethod
//    public void tearDown() {
//        // Any cleanup operations if necessary.
//        
//    }
}
