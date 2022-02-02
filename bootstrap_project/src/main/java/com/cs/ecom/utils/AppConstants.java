package com.cs.ecom.utils;

public class AppConstants {

	
	public static final String BYPASS_API_PREFIX = "/mobile/";
	public static final String REST_API_PREFIX = "/rest_api/";
	
	public static final String SESSION_ATTR_NAME_USER = "session_response_obj_usr";
	public static final String SESSION_ATTR_NAME_APPLICANT = "session_obj_applicant";
	
	public static final String RESPONSE_CODE_SUCCESS = "200";
    public static final String RESPONSE_CODE_DUPLICATE = "210";
    public static final String RESPONSE_CODE_NOT_FOUND = "404";
    public static final String RESPONSE_CODE_BAD_REQUEST = "400";
    public static final String RESPONSE_CODE_CREATED = "201";
    public static final String RESPONSE_CODE_UNAUTHORIZED = "401";
    public static final String RESPONSE_CODE_UNSUPPORTED_TYPE = "415";
    public static final String RESPONSE_CODE_SERVER_ERROR = "500";
    public static final String RESPONSE_CODE_NO_DATA = "505";
    public static final String RESPONSE_CODE_CHILD = "509";
    public static final String RESPONSE_TEXT_SUCCESS = "success";
    public static final String RESPONSE_TEXT_SUCCESS_PASSWORD = "Your password has been change successfully";
    public static final String RESPONSE_TEXT_ERROR = "Server Error. Contact Administrator";
    public static final String RESPONSE_TEXT_INVALID_EMAIL_OR_DOB = "Invalid Email or date of birth";
    public static final String RESPONSE_TEXT_NO_DATA = "No Data Found";
    public static final String RESPONSE_TEXT_DATA_FOUND = "Data Found";
    public static final String RESPONSE_TEXT_INVALID_PHONE_NUMBER = "Invalid Phone Number";
    public static final String RESPONSE_TEXT_INVALID_EMAIL_DOB = "Invalid Email Or Date Of Birth";
    public static final String RESPONSE_TEXT_EMAIL_NOT_EXIST = "Email not exist";
    public static final String RESPONSE_CHECKIN_SUCCESSFULL = "CheckIn Successfully";
    public static final String RESPONSE_ALREADY_CHECKIN = "Already CheckIn";
    public static final String RESPONSE_CHECKOUT_SUCCESSFULL = "CheckOut Successfully";
    public static final String RESPONSE_CHECKIN_FAIL = "CheckIn Fail";
    public static final String RESPONSE_CHECKIN_FAIL_BEFORE_30_MIN = "Not CheckIn before 30 minutes shift start";
    public static final String RESPONSE_CHECKOUT_FAIL = "CheckOut Fail";
    public static final String RESPONSE_TEXT_DATA_SAVED = "Data Saved Successfully";
    public static final String RESPONSE_TEXT_NOTIFY_SENT = "Notification Sent Successfully";
    public static final String RESPONSE_TEXT_SENT_APPROVAL = "Your request has been sent for approval.";
    public static final String RESPONSE_TEXT_INVALID_PARAMS = "Invalid Parameters";
    public static final String RESPONSE_TEXT_INVALID_CREDS = "Invalid Credentials. Contact Admin.";
    public static final String RESPONSE_TEXT_UNAUTHORIZED = "Unauthorized Access";
    public static final String RESPONSE_TEXT_DUPLICATE = "Duplicate Data";
    public static final String RESPONSE_TEXT_CHILD = "Child Record Exist";
    public static final String RESPONSE_TEXT_INVALID_EMAIL = "Email not associated with any account.";
    public static final String RESPONSE_TEXT_SAVED_WITHOUT_NOTIFY = "Data saved but failed to send notification";
    public static final String RESPONSE_TEXT_CAN_NOT_INACTIVE = "Record cannot be inactive because applicants exist in this record";
    public static final String RESPONSE_TEXT_CAN_NOT_INACTIVE_CLIENT = "Record cannot be inactive because products exist in this record";
    
    public static final int RECORD_NUMER_0 = 0;
    public static final int RECORD_NUMER_1000 = 1000;

    public static final String STATUS_INACTIVE = "Inactive";
	public static final String STATUS_ACTIVE = "Active";
	public static final String STATUS_LOCK = "Lock";
	public static final String STATUS_IN_PROGRESS = "In Progress";
	public static final String STATUS_COMPLETED = "Completed";
	
	public static final String TEXT_NO = "No";
	public static final String TEXT_YES = "Yes";
	public static final String TEXT_UNDEFINED = "undefined";
	
	public static final String DIR_IMAGES = "images";

	public static final Long RIGHTS_ALLOWED = 1l;
	public static final Long RIGHTS_NOT_ALLOWED = 0l;

	public static final Long ID_DASHBOARD = 1l;

	public static final Long USER_ROLE_AGENT = 6l;
	public static final Long USER_ROLE_ADMIN = 1l;
	public static final Long USER_ROLE_HR = 3l;
	public static final Long USER_ROLE_USER = 2l;

	public static final String APPLICANT_PHASE_ONE = "1";
	public static final String APPLICANT_PHASE_TWO = "2";
	
	public static final String APPLIED = "Applied";
	public static final String APPROVED = "Approved";

	public static final String PHASE_STATUS_NEW = "New";
	public static final String PHASE_STATUS_VIEWED = "Viewed";
	public static final String PHASE_STATUS_CONTACTED = "Contacted";
	public static final String PHASE_STATUS_NO_CALL_NO_SHOW = "No call no show";
	public static final String PHASE_STATUS_RESOLVED = "Resolved";
	public static final String PHASE_STATUS_REJECTED = "Rejected";
	public static final String PHASE_STATUS_SHORT_LISTED = "ShortList";
	public static final String PHASE_STATUS_HIRED = "Hired";
	public static final String PHASE_STATUS_JOINING = "Joining";
	public static final String PHASE_STATUS_APPOINTED = "Appointed";
	public static final String PHASE_STATUS_TERMINATED = "Terminated";
	public static final String PHASE_STATUS_RESIGNED = "Resigned";

	public static final String DIR_NET_SPEED = "netSpeed";
	public static final String DIR_COURSE = "course";
	
	public static final String LATE = "Late";
	public static final String EARLY = "Early";
	public static final String ON_TIME = "On Time";
	public static final String ABSENT = "Absent";
	public static final String LEAVE = "Leave";
	public static final String CHECK_IN = "check-in";
	public static final String CHECK_OUT = "check-out";

	
	public static final String LEAVE_TYPE_CASUAL = "Casual";
	public static final String LEAVE_TYPE_ANNUAL = "Annual";
	public static final String LEAVE_TYPE_MEDICAL = "Medical";
	public static final String RESPONSE_NO_SHIFT_TIME = "No Shift Allotted Contact HR";
	public static final String RESPONSE_NO_ROLE = "Please Contact HR For User Activation";
	
	public static final String TYPE_APPLICANT = "Applicant";
	public static final String TYPE_TRAINEE = "Trainee";
	public static final String TYPE_EMPLOYEE = "Employee";


	
	//For Background Process for No Call No Show Calculation
	public static final String NCNS_CONFIG_PARAM_STATUS = "NCNS_STATUS";
	public static final String NCNS_CONFIG_PARAM_LAST_RUN = "NCNS_LAST_RUN";
	public static final String NCNS_CONFIG_PARAM_RUN_AFTER_HRS = "NCNS_RUN_AFTER_HRS";
	public static final String NCNS_STATUS_RUNNING = "RUNNING";
	public static final String NCNS_STATUS_STOPPED = "STOPPED";
	public static final String DAY_LIGHT_SAVING = "DAY_LIGHT_SAVING";
	public static final String CURRENT_TIME_ZONE = "CURRENT_TIME_ZONE";
	public static final String ADD_ONE_HOUR = "ADD ONE HOUR";
	public static final String SUB_ONE_HOUR = "SUB ONE HOUR";
	public static final String PST = "PST";
	public static final String MST = "MST";
	
	public static final int PAYROLL_START_DATE = 21;
	public static final int PAYROLL_END_DATE = 20;
    
}
