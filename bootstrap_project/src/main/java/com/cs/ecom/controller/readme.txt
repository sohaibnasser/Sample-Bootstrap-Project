This folder will contain controller

public class ApplicantController {

	public static RestResponse getAllApplicants(HttpServletRequest request, ApplicantRepo applicantRepo) {
		RestResponse restResponse = new RestResponse();
		try {
			Iterable<ApplicantDto> dataList = applicantRepo.findAll();
			restResponse = new RestResponse(AppConstants.RESPONSE_TEXT_SUCCESS, AppConstants.RESPONSE_CODE_SUCCESS, AppConstants.RESPONSE_TEXT_DATA_FOUND,
					dataList);
		} catch (Exception e) {
			restResponse = new RestResponse(AppConstants.RESPONSE_TEXT_ERROR, AppConstants.RESPONSE_CODE_NO_DATA, AppConstants.RESPONSE_TEXT_NO_DATA, null);
			e.printStackTrace();
		}
		return restResponse;
	}

}