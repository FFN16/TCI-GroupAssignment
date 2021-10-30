package services.exposed.invigilator;

/**
 * returns a specific Alert object to the EFIT client. the efit client uses this for displaying info in its UI.
 */
public enum Alert {
    ALERT_OK, // everything ok
    ALERT_YELLOW, // something wrong, retry
    ALERT_ERROR,  // error, try other way (f.i. using the invigilator
    ALERT_EXAM_NOT_STARTED, // when exam is not started yet
    ALERT_EXAM_ENDED // when exam is already ended
}
