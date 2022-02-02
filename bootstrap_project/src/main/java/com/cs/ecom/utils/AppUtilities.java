package com.cs.ecom.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class AppUtilities {

	public static String createToken(String extension) {
		String token = "";
		Random r = new java.util.Random();
		token = Long.toString(r.nextLong() & Long.MAX_VALUE, 36) + extension;
		return token;
	}

	public static String createNotificationId() {
		String s = "" + new Date().getTime();
		s = s.substring(3, s.length() - 4) + new Random().nextInt(999999);
		return s;
	}

	public static String createToken() {
		String token = "";
		Random r = new java.util.Random();
		token = Long.toString(r.nextLong() & Long.MAX_VALUE, 36);
		return token;
	}

	public static String getRandomNumberString() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public static String getPropertyFromAppProperties(String propertyName) {
		Properties props = null;
		String propValue = null;
		try {
			String resourceName = "application.properties";
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props = new Properties();
			try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
				props.load(resourceStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		propValue = props.getProperty(propertyName);
		return propValue;
	}

	public static String resetPasswordToken(int len) {
		SecureRandom rnd = new SecureRandom();
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String getCurrentFormattedDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyhhmmss");
		return LocalDateTime.now().format(formatter);
	}

	public static String formatDateDMY(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}

	public static String formatDateByFormat(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public static String formatDateyyyyMMdd(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static String formatDateDMYHM(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formatter.format(date);
	}

	public static String removeBase64ImageAssociation(String base64String) {
		String[] parts = base64String.split(",");
		return parts[1];
	}

	public static Date getFirstDateOfCurrentMonth() {
		LocalDate today = LocalDate.now();
		return java.sql.Date.valueOf(today.withDayOfMonth(1));
	}

	public static Date getFirstDateOf100YrsBack() {
		LocalDate today = LocalDate.now();
		today = today.minusYears(100);
		return java.sql.Date.valueOf(today.withDayOfMonth(1));
	}

	public static Date getLastDateOfCurrentMonth() {
		LocalDate today = LocalDate.now();
		return java.sql.Date.valueOf(today.withDayOfMonth(today.lengthOfMonth()));
	}

	public static Pageable gotoPage(int page, int itemPerPage) {
		Pageable request = (Pageable) PageRequest.of(page, itemPerPage);
		return request;
	}

	public static <T> List<T> removeDuplicates(List<T> list) {

		// Create a new LinkedHashSet
		Set<T> set = new LinkedHashSet<>();

		// Add the elements to set
		set.addAll(list);

		// Clear the list
		list.clear();

		// add the elements of set
		// with no duplicates to the list
		list.addAll(set);

		// return the list
		return list;
	}

	public static double roundUp(double value, int places) {
		double scale = Math.pow(10, places);
		return Math.round(value * scale) / scale;
	}

	public static double roundToHalf(double d) {
		return Math.round(d * 2) / 2.0;
	}

	public static Date getFirstDayOfMonthAndYear(Long month, Long year) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.YEAR, year.intValue());
		c.set(Calendar.MONTH, month.intValue());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		System.out.println(c.getTime());
		return c.getTime();
	}

	public static Date getFirstDayOfCurrentYear() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, c.getActualMinimum(Calendar.DAY_OF_YEAR));
		return c.getTime();
	}

	public static Date getLastDayOfCurrentYear() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
		return c.getTime();
	}

	public static Date getLastDayOfMonthAndYear(Long month, Long year) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DATE));
		c.set(Calendar.YEAR, year.intValue());
		c.set(Calendar.MONTH, month.intValue());
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		System.out.println(c.getTime());
		return c.getTime();
	}

	@SuppressWarnings("deprecation")
	public static String getDayOfWeek(Date inputDate) {
		String dayOfWeek = null;
		String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

		try {
			dayOfWeek = days[inputDate.getDay()];
		} catch (Exception e) {
			System.out.println(e);
		}

		return dayOfWeek;
	}

	public static Date getNextDayOfWeek(String day) {

		LocalDate ld = LocalDate.now();
		String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		if (day == days[0]) {
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		} else if (day.equalsIgnoreCase(days[1])) {
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
		} else if (day.equalsIgnoreCase(days[2])) {
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
		} else if (day.equalsIgnoreCase(days[3])) {
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
		} else if (day.equalsIgnoreCase(days[4])) {
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
		} else if (day.equalsIgnoreCase(days[5])) {
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
		} else if (day.equalsIgnoreCase(days[6])) {
			ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		}

		return java.sql.Date.valueOf(ld);
	}

	public static String resizeImage(String imageString) {

		String result = "";
		try {
			String[] strings = imageString.split(",");
			BufferedImage bi = decodeToImage(strings[1]);
			String extension;
			switch (strings[0]) {// check image's extension
			case "data:image/jpeg;base64":
				extension = "jpeg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			default:// should write cases for more images types
				extension = "jpg";
				break;
			}
			bi = resize(bi, 300);
			result = strings[0] + "," + encodeToString(bi, extension);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String resizeImageAndSaveToDisk(String imageString, String imageName, String type, Long id, String fileUploadDir) {

		String result = "";
		try {
			String[] strings = imageString.split(",");
			BufferedImage bi = decodeToImage(strings[1]);
			String extension;
			switch (strings[0]) {// check image's extension
			case "data:image/jpeg;base64":
				extension = "jpeg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			default:// should write cases for more images types
				extension = "jpg";
				break;
			}
			imageName = imageName.trim().replace(" ", "_").replace("/", "_").replace("\\", "_").replace(".", "_");
			bi = resize(bi, 300);

			result = type + "___" + imageName + "_" + id + "--" + extension;
			Path newFilePath = Paths.get(fileUploadDir + File.separator + type + File.separator + imageName + "_" + id + "." + extension);
			if (Files.notExists(newFilePath)) {
				Files.createFile(newFilePath);
			}
			// File file = ResourceUtils.getFile("classpath:app_images"+File.separator+ type
			// +File.separator + imageName+"_"+id+"."+extension);
			ImageIO.write(bi, extension, newFilePath.toFile());

			// result = strings[0]+","+encodeToString(bi, extension);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String saveImageToDiskWithDir(String imageString, String imageName, String type, Long id, String fileUploadDir) {
		BufferedImage bi = null;
		String result = "";
		try {
			String[] strings = imageString.split(",");
			bi = decodeToImage(strings[1]);
			String extension;
			switch (strings[0]) {
			case "data:image/jpeg;base64":
				extension = "jpeg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			default:
				extension = "jpg";
				break;
			}
			imageName = imageName.trim().replace(" ", "_").replace("/", "_").replace("\\", "_").replace(".", "_").replace("-", "_").replace("__", "_")
					.replaceAll("[^a-zA-Z0-9]", "_");
			long timeAppend = new Date().getTime();
			result = type + "___" + timeAppend + "_" + id + "--" + extension;
			Path newFilePath = Paths.get(fileUploadDir + File.separator + type + File.separator + timeAppend + "_" + id + "." + extension);
			if (Files.notExists(newFilePath)) {
				Files.createFile(newFilePath);
			} else {
				Files.delete(newFilePath);
				Files.createFile(newFilePath);
			}
			ImageIO.write(bi, extension, newFilePath.toFile());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bi != null) {

				bi.flush();
			}
		}
		return result;
	}

	public static String saveImageToDisk(String imageString, Long id, String fileUploadDir) {
		BufferedImage bi = null;
		String result = "";
		try {
			String[] strings = imageString.split(",");
			bi = decodeToImage(strings[1]);
			String extension;
			switch (strings[0]) {
			case "data:image/jpeg;base64":
				extension = "jpeg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			default:
				extension = "jpg";
				break;
			}
			Long dateTime = new Date().getTime();
			result = dateTime + "_" + id + "--" + extension;
			Path newFilePath = Paths.get(fileUploadDir + File.separator + dateTime + "_" + id + "." + extension);
			if (Files.notExists(newFilePath)) {
				Files.createFile(newFilePath);
			} else {
				Files.delete(newFilePath);
				Files.createFile(newFilePath);
			}
			ImageIO.write(bi, extension, newFilePath.toFile());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bi != null) {

				bi.flush();
			}
		}
		return result;
	}

	public static String saveImageToDiskNew(String imageString, String imageName, String type, Long id, String fileUploadDir) {
		FileOutputStream osf = null;
		String result = "";
		try {
			String[] strings = imageString.split(",");
			String extension;
			switch (strings[0]) {
			case "data:image/jpeg;base64":
				extension = "jpeg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			default:
				extension = "jpg";
				break;
			}
			imageName = imageName.trim().replace(" ", "_").replace("/", "_").replace("\\", "_").replace(".", "_");
			result = type + "___" + imageName + "_" + id + "--" + extension;
			Path newFilePath = Paths.get(fileUploadDir + File.separator + type + File.separator + imageName + "_" + id + "." + extension);
			if (Files.notExists(newFilePath)) {
				Files.createFile(newFilePath);
			} else {
				Files.delete(newFilePath);
				Files.createFile(newFilePath);
			}
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] imageByte = decoder.decode(strings[1]);
			osf = new FileOutputStream(newFilePath.toFile());
			osf.write(imageByte);
			osf.flush();
			osf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String savePdfToDisk(String pdfString, String fileName, String fileUploadDir) {
		FileOutputStream osf = null;
		String result = "";
		try {
			String[] strings = pdfString.split(",");
			fileName = fileName.trim().replace(" ", "_").replace("/", "_").replace("\\", "_").replace(".", "_");
			result = "pdf" + "___" + fileName + "_" + "--" + "pdf";
			Path newFilePath = Paths.get(fileUploadDir + File.separator + "pdf" + File.separator + fileName + "_" + "." + "pdf");
			if (Files.notExists(newFilePath)) {
				Files.createFile(newFilePath);
			} else {
				Files.delete(newFilePath);
				Files.createFile(newFilePath);
			}
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] imageByte = decoder.decode(strings[1]);
			osf = new FileOutputStream(newFilePath.toFile());
			osf.write(imageByte);
			osf.flush();
			osf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean deleteFile(String imageUrl, String fileUploadDir) {
		boolean result = false;
		try {
			Path path = Paths.get(fileUploadDir + imageUrl.replace("___", "//").replace("--", "."));
			if (Files.exists(path)) {
				Files.delete(path);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static BufferedImage resize(BufferedImage src, int targetSize) {
		if (targetSize <= 0) {
			return src; // this can't be resized
		}
		int targetWidth = targetSize;
		int targetHeight = targetSize;
		float ratio = ((float) src.getHeight() / (float) src.getWidth());
		if (ratio <= 1) { // square or landscape-oriented image
			targetHeight = (int) Math.ceil((float) targetWidth * ratio);
		} else { // portrait image
			targetWidth = Math.round((float) targetHeight / ratio);
		}
		BufferedImage bi = new BufferedImage(targetWidth, targetHeight,
				src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // produces
																												// a
																												// balanced
																												// resizing
																												// (fast
																												// and
																												// decent
																												// quality)
		g2d.drawImage(src, 0, 0, targetWidth, targetHeight, null);
		g2d.dispose();
		return bi;
	}

	public static String encodeToString(BufferedImage image, String type) {
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			Base64.Encoder encoder = Base64.getEncoder();
			imageString = encoder.encodeToString(imageBytes);

			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageString;
	}

	public static BufferedImage decodeToImage(String imageString) {
		BufferedImage image = null;
		byte[] imageByte;
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			imageByte = decoder.decode(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	public static Date addDaysToDate(Date currentDate, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	public static Date subtractDaysToDate(Date currentDate, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DAY_OF_MONTH, -days);
		return cal.getTime();
	}

	public static Date getDateFromMilliSeconds(long milliSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return calendar.getTime();
	}

	public static Date atEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 000);
		return calendar.getTime();
	}

	public static Date atEndOfDayWithSec(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date atCenterOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 000);
		return calendar.getTime();
	}

	public static Date atStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 111);
		return calendar.getTime();
	}

	public static Date getDateWithZeroTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date addHoursToDate(Date date, int hrz) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY + hrz);
		return calendar.getTime();
	}

	public static Date subHoursToDate(Date date, int hrz) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY - hrz);
		return calendar.getTime();
	}

	public static Date subtractSecondFromDate(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 111);
		calendar.set(Calendar.SECOND, (calendar.get(Calendar.SECOND) - seconds));
		return calendar.getTime();
	}

	public static List<Date> getNextDatesBasedOnDays(Date currentDate, String daysName, int daysToAddInDate) {
		if (currentDate != null && daysName != null) {
			List<Date> returnList = new ArrayList<Date>();
			daysName = daysName.toUpperCase();
			String[] daysArray = daysName.split(",");
			List<String> daysList = new ArrayList<String>();
			for (String string : daysArray) {
				daysList.add(string.trim());
			}
			LocalDateTime ldt = Instant.ofEpochMilli(currentDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
			for (int i = 0; i < 30; i++) {
				if (daysList.contains(ldt.getDayOfWeek().toString())) {
					returnList.add(Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
				}
				ldt = ldt.plusDays(1);
			}

			return returnList;
		} else {
			return null;
		}

	}

	public static Date getZeroTimeDate(Date fecha) {
		Date res = fecha;
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		res = calendar.getTime();

		return res;
	}

	public static Date setTimeToCurrentDate(Date timeDate) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(timeDate);
		calendar.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE));
		calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
		calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
		timeDate = calendar.getTime();
		return timeDate;
	}

	public static Date setTimeToCurrentDateAddOneDay(Date timeDate) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(timeDate);
		calendar.set(Calendar.DATE, Calendar.getInstance().get(Calendar.DATE));
		calendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
		calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
		calendar.add(Calendar.DATE, 1);
		timeDate = calendar.getTime();
		return timeDate;
	}


	public static String getTimeDifferenceBtwDates(Date startDate, Date endDate) {

		// milliseconds
		long different = endDate.getTime() - startDate.getTime();

		System.out.println("startDate : " + startDate);
		System.out.println("endDate : " + endDate);
		System.out.println("different : " + different);

		long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		// long daysInMilli = hoursInMilli * 24;

		// long elapsedDays = different / daysInMilli;
		// different = different % daysInMilli;

		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;

		long elapsedMinutes = different / minutesInMilli;
		different = different % minutesInMilli;

		long elapsedSeconds = different / secondsInMilli;

		System.out.printf("%d hours, %d minutes, %d seconds%n", elapsedHours, elapsedMinutes, elapsedSeconds);

		String time = elapsedHours + "" + ":" + elapsedMinutes + "";

		return time;
	}

	public static long getTimeFromDateInMilliSeconds(Date date) {

		// milliseconds
		long dateTime = date.getTime();
		long secondsInMilli = 1000;
		long elapsedSeconds = dateTime / secondsInMilli;

		return elapsedSeconds;
	}

	public static Long getHoursDifferenceBtwDates(Date startDate, Date endDate) {

		// milliseconds
		long different = endDate.getTime() - startDate.getTime();
		long difference_In_Hours = (different / (1000 * 60 * 60)) % 24;
		return difference_In_Hours;
	}

	public static Long getMinutsDifferenceBtwDates(Date startDate, Date endDate) {
		long different = endDate.getTime() - startDate.getTime();
		long difference_In_Minutes = (different / (1000 * 60));
		return difference_In_Minutes;
	}

	public static String getDailyHoures(String shiftHours, String dailyHours) {

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(shiftHours);
			date2 = format.parse(dailyHours);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		// long difference = date2.getTime() - date1.getTime();
		if (date2.getTime() == date1.getTime() || date2.getTime() > date1.getTime()) {
			return shiftHours;

		} else {
			return dailyHours;

		}

	}

	public static String getEmpNo(String Fnam, String Lnam, long applicantId) {
		String empNo = "";
		char FnamFch = Fnam.charAt(0);
		char LnamFch = Lnam.charAt(0);
		int length = String.valueOf(applicantId).length();
		if (length == 1) {
			empNo = FnamFch + "" + LnamFch + "0000" + applicantId + "";
		} else if (length == 2) {
			empNo = FnamFch + "" + LnamFch + "000" + applicantId + "";
		} else if (length == 3) {
			empNo = FnamFch + "" + LnamFch + "00" + applicantId + "";
		} else if (length == 4) {
			empNo = FnamFch + "" + LnamFch + "0" + applicantId + "";
		} else {
			empNo = FnamFch + "" + LnamFch + applicantId + "";
		}
		return empNo;
	}

	public static Date getDateFromString(String date) throws ParseException {
		Date d = null;
		try {
			String pattern = "MM/dd/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);

			d = simpleDateFormat.parse(date);
			// System.out.println(d);
		} catch (Exception bug) {
			bug.printStackTrace();
		}
		return d;
	}

	public static Date getDateFromStringWithFormat(String date, String format) {
		Date d = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			d = simpleDateFormat.parse(date);
			// System.out.println(d);
		} catch (Exception bug) {
			bug.printStackTrace();
		}
		return d;
	}

	public static Date getDateFromTimeStringWithFormat(String time) {
		Date returnDate = null;
		Calendar cal = Calendar.getInstance();
		try {

			String[] s = time.split(":");
			cal.set(Calendar.HOUR, Integer.parseInt(s[0]));
			cal.set(Calendar.MINUTE, Integer.parseInt(s[1]));
			cal.set(Calendar.SECOND, 0);
			returnDate = cal.getTime();
		} catch (Exception bug) {
			bug.printStackTrace();
			cal = null;
		}
		return returnDate;
	}

	public static Date dateWitoutTime(Date date) {
		Date returnDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			returnDate = formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	public static List<Date> getLastDaysWorkingOrOther(int daysRequired, boolean weekendRequired) {
		int i = 0;
		List<Date> dateList = new ArrayList<Date>();
		while (dateList.size() < daysRequired) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -i);
			Date date = cal.getTime();
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if (weekendRequired) {
					dateList.add(dateWitoutTime(date));
				}
			} else {
				dateList.add(dateWitoutTime(date));
			}
			i++;

		}
		return dateList;
	}

	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		startCal.add(Calendar.DAY_OF_MONTH, 1);
		while (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
			// excluding start date
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workDays++;
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			Date d = startCal.getTime();
			if (AppUtilities.addDaysToDate(d, 1).getTime() > endCal.getTimeInMillis()) {
				break;
			}
		}
		; // excluding end date
		System.out.println(workDays);
		return workDays;
	}

	public static int getWorkingDaysBetweenTwoDatesEndDateInclude(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		startCal.add(Calendar.DAY_OF_MONTH, 1);
		while (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
			// excluding start date
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workDays++;
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
//			Date d = startCal.getTime();
//			if(AppUtilities.addDaysToDate(d, 1).getTime()>endCal.getTimeInMillis()) {
//				break;
//			}
		}
		; // excluding end date
		System.out.println(workDays);
		return workDays;
	}

	public static int getWorkingDaysBetweenTwoDatesStartDateInclude(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

//		startCal.add(Calendar.DAY_OF_MONTH, 1);
		while (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
			// excluding start date
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workDays++;
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			Date d = startCal.getTime();
			if (AppUtilities.addDaysToDate(d, 1).getTime() > endCal.getTimeInMillis()) {
				break;
			}
		}
		; // excluding end date
		System.out.println(workDays);
		return workDays;
	}

	public static int getWorkingDaysBetweenTwoDatesBothDateInclude(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return 0;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

//		startCal.add(Calendar.DAY_OF_MONTH, 1);
		while (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
			// excluding start date
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				++workDays;
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
//			Date d = startCal.getTime();
//			if(AppUtilities.addDaysToDate(d, 1).getTime()>endCal.getTimeInMillis()) {
//				break;
//			}
		}
		; // excluding end date
		System.out.println(workDays);
		return workDays;
	}

	public static List<Date> getWorkingDatesBetweenTwoDates(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		List<Date> dateList = new ArrayList<Date>();

		// Return 0 if start and end are the same
		if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
			return null;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		startCal.add(Calendar.DAY_OF_MONTH, 1);
		while (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
			// excluding start date
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				dateList.add(startCal.getTime());
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			Date d = startCal.getTime();
			if (AppUtilities.addDaysToDate(d, 1).getTime() > endCal.getTimeInMillis()) {
				break;
			}
		}
		; // excluding end date
			// System.out.println(dateList);
		return dateList;
	}

	 

	public static Date getThisMonthPayrollStartDate(int payrollDate) {
		Date returnDate = null;

		try {
			Calendar calendar = Calendar.getInstance();
			if (calendar.get(Calendar.DATE) < 21) {
				calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
			}
			calendar.set(Calendar.DATE, 21);
			returnDate = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			returnDate = null;
		}

		return returnDate;
	}

	public static Date setTimeFromDateToAnotherDate(Date getTimeFromDate, Date setTimeToDate) {

		Calendar fromCal = Calendar.getInstance();
		Calendar toCal = Calendar.getInstance();
		fromCal.setTime(getTimeFromDate);
		toCal.setTime(setTimeToDate);

		toCal.set(Calendar.HOUR_OF_DAY, fromCal.get(Calendar.HOUR_OF_DAY));
		toCal.set(Calendar.MINUTE, fromCal.get(Calendar.MINUTE));
		toCal.set(Calendar.SECOND, fromCal.get(Calendar.SECOND));
		return toCal.getTime();
	}
	
	public static Date addSubMinToDate(Date date, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + min);
		return calendar.getTime();
	}
	public static long compareDatesOnly(Date date1, Date date2) {
		long milliseconds1 = date1.getTime();
		long milliseconds2 = date2.getTime();
		long diff = milliseconds2 - milliseconds1;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

}