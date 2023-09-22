package utility;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

/**
 * Custom WebDriver listener for reporting events during test execution.
 */
public class EventReporter implements WebDriverListener {

    /**
     * Before any WebElement call, print the action being performed.
     *
     * @param element The WebElement being interacted with.
     * @param method  The method being called.
     * @param args    The arguments passed to the method.
     */
    public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
        System.out.println("Performing action " + method.getName() + " on " + element.getAccessibleName());
    }

    /**
     * After any WebElement call, print the action that was performed.
     *
     * @param element The WebElement that was interacted with.
     * @param method  The method that was called.
     * @param args    The arguments passed to the method.
     * @param result  The result of the method call.
     */
    public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
        System.out.println("Action Performed  " + method.getName());
    }

    /**
     * Before navigating to a URL, print the URL.
     *
     * @param navigation The WebDriver navigation object.
     * @param url        The URL to navigate to.
     */
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        System.out.println("Navigating to URL: " + url);
    }

    /**
     * After navigating to a URL, print the URL.
     *
     * @param navigation The WebDriver navigation object.
     * @param url        The URL that was navigated to.
     */
    public void afterTo(WebDriver.Navigation navigation, String url) {
        System.out.println("Navigated to URL: " + url);
    }

    /**
     * Before finding an element, print the locator.
     *
     * @param driver  The WebDriver instance.
     * @param locator The locator used to find the element.
     */
    public void beforeFindElement(WebDriver driver, By locator) {
        System.out.println("Finding element: " + locator);
    }

    /**
     * After finding an element, print the locator.
     *
     * @param driver  The WebDriver instance.
     * @param locator The locator used to find the element.
     * @param result  The WebElement that was found.
     */
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        System.out.println("Element found: " + locator);
    }

    /**
     * Before any alert call, print the alert text.
     *
     * @param alert  The Alert object.
     * @param method The method being called.
     *
    /**
     * Before accepting an alert, print that an alert is about to be accepted.
     *
     * @param alert The Alert object.
     */
    public void beforeAccept(Alert alert) {
        System.out.println("About to accept an alert");
    }

    /**
     * After accepting an alert, print that the alert was accepted.
     *
     * @param alert The Alert object.
     */
    public void afterAccept(Alert alert) {
        System.out.println("Accepted the alert");
    }

    /**
     * After dismissing an alert, print that the alert was dismissed.
     *
     * @param alert The Alert object.
     */
    public void afterDismiss(Alert alert) {
        System.out.println("Dismissed the alert");
    }

    /**
     * Before sending keys to an element, print the new value.
     *
     * @param element     The WebElement receiving keys.
     * @param keysToSend  The keys being sent.
     */
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("Changing value of element " + element + " to: " + keysToSend[0]);
    }

    /**
     * After sending keys to an element, print the new value.
     *
     * @param element     The WebElement that received keys.
     * @param keysToSend  The keys that were sent.
     */
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        System.out.println("Changed value of element " + element + " to: " + keysToSend[0]);
    }

    /**
     * Before any timeouts call, print the details of the waiting operation.
     *
     * @param timeouts The WebDriver Timeouts object.
     * @param method   The method being called.
     * @param args     The arguments passed to the method.
     */
    public void beforeAnyTimeoutsCall(WebDriver.Timeouts timeouts, Method method, Object[] args) {
        System.out.println("Waiting for: " + timeouts);
    }
}
