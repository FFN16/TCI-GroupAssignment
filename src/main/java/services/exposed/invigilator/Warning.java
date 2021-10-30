package services.exposed.invigilator;

/**
 * warnings invigilators can get from the system for a specific student during an exam
 */
public enum Warning {
    NO_WARNING, VM_DETECTED, RUNNING_IN_VM_DETECTED, EXTERNAL_COMMUNICATIONPATH_DETECTED;
}
