package execution;

/**
 * an examexecution consists of the examsetup with all examstudents who logged in for the exam with all of their data.
 *
 * it also contains methods for getting A COPY OF the studentexams objects.
 *
 * When all results of an exam are final (exam marks are registered in another system), the examexecution will be set
 * to status 'finalized'. This means its data can be removed by the data manager, and  only the metadata remains:
 * this is
 * the name of the exam, the date of the exam
 * the number of generated classcodes
 * the number of students which used each classcode
 * the exam duration per used classcode
 *
 * cleanup of the data can only be performed by the datamanager. when no finalization has taken place yet, the data cannot
 * be cleaned, but a DataNotFinalizedException is thrown.
 *
 * the examID of the examExecution can be returned.
 *
 * after cleaning of data: any method which tries to get detailed information, will throw an DataCleanedException
 *
 *
 */
public class ExamExecution {
    // TODO: the appropiate methods

}
