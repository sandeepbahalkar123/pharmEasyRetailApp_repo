package com.scorg.farmaeasy.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.scorg.farmaeasy.R;
import com.scorg.farmaeasy.interfaces.CheckIpConnection;
import com.scorg.farmaeasy.interfaces.DatePickerDialogListener;
import com.scorg.farmaeasy.model.responseModel.batchlist.BatchList;
import com.scorg.farmaeasy.ui.fragments.ProductFragment;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethods {

    private static final String TAG = "PharmEasy/CommonMethods";
    private static boolean encryptionIsOn = true;
    private static String aBuffer = "";
    private static CheckIpConnection mCheckIpConnection;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DatePickerDialogListener mDatePickerDialogListener;

    public static void showToast(Context context, String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    public static void showIPAlertDialog(Context activity, String dialogHeader, CheckIpConnection checkIpConnection) {
        final Context mContext = activity;
        mCheckIpConnection = checkIpConnection;
        final Dialog dialog = new Dialog(activity);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ok_cancel);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        if (dialogHeader != null)
            ((TextView) dialog.findViewById(R.id.textView_dialog_heading)).setText(dialogHeader);

        dialog.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etServerPath = (EditText) dialog.findViewById(R.id.et_server_path);

                if (isValidIP(etServerPath.getText().toString())) {
                    String mServerPath = etServerPath.getText().toString();
                    Log.e(TAG, "INTRANET SERVER PATH===" + mServerPath);
                    mCheckIpConnection.onOkButtonClickListner(mServerPath, mContext, dialog);
                } else {
                    Toast.makeText(mContext, R.string.error_in_ip, Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                ((Activity) mContext).finish();
            }
        });
        dialog.show();

    }

    public static void showInputDialog(Context context, String message, BatchList batchList, ProductFragment.DialogInputListener dialogInputListener) {
        final Dialog dialog = new Dialog(context);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.input_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        EditText inputBox = ((EditText) dialog.findViewById(R.id.editTextQuantity));
        //Forcefully to open soft keyborad
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        if (batchList.getSaleQTY() != 0) {
            inputBox.setText(String.valueOf(batchList.getSaleQTY()));
            inputBox.setSelection(String.valueOf(batchList.getSaleQTY()).length());
        }

        dialog.findViewById(R.id.button_ok).setOnClickListener(v -> {
            if (!inputBox.getText().toString().isEmpty()) {
                int saleQuantity = Integer.parseInt(inputBox.getText().toString());
                if (batchList.getClosingStock() < saleQuantity)
                    CommonMethods.showToast(context, context.getString(R.string.input_sale_quantity_message));
                else {
                    //Forcefully to close soft keyborad
                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(inputBox.getWindowToken(), 0);
                    dialogInputListener.inputValue(saleQuantity);
                    dialog.dismiss();

                }
            } else
                CommonMethods.showToast(context, message);
        });
        dialog.findViewById(R.id.button_cancel).setOnClickListener(v -> {
            //Forcefully to close soft keyborad
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(inputBox.getWindowToken(), 0);
            dialog.dismiss();
        });
        dialog.show();

    }

//    public static Double getMRP(BatchList batchList,int quantity) {
//        Double mrp=0.0;
//
//        batchList.getSaleRate();
//        return mrp;
//    }

    public static String toCamelCase(String input) {
        if (input == null)
            return "null";

        if (!input.equals("")) {
            StringBuilder result = new StringBuilder();
            char firstChar = input.charAt(0);
            result.append(Character.toUpperCase(firstChar));
            for (int i = 1; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                char previousChar = input.charAt(i - 1);
                if (previousChar == ' ')
                    result.append(Character.toUpperCase(currentChar));
                else
                    result.append(Character.toLowerCase(currentChar));
            }
            return result.toString();
        } else {
            return input;
        }
    }


    // 1ˢᵗ, 2ⁿᵈ, 3ʳᵈ, 4ᵗʰ
    public static String ordinal(String number) {
        try {

            int i = Integer.parseInt(number);
            String[] sufixes = new String[]{"ᵗʰ", "ˢᵗ", "ⁿᵈ", "ʳᵈ", "ᵗʰ", "ᵗʰ", "ᵗʰ", "ᵗʰ", "ᵗʰ", "ᵗʰ"};
            switch (i % 100) {
                case 11:
                case 12:
                case 13:
                    return i + "ᵗʰ";
                default:
                    return i + sufixes[i % 10];
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void showSnack(View mViewById, String msg) {
        if (mViewById != null) {
            Snackbar.make(mViewById, msg, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Log.d(TAG, "null snacbar view" + msg);
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    /**
     * Email validator
     *
     * @param emailId
     * @return
     */
    public final static boolean isValidEmail(CharSequence emailId) {
        if (emailId == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
        }
    }


    public static void hideKeyboard(Activity cntx) {
        // Check if no view has focus:
        View view = cntx.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) cntx.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static TextDrawable getTextDrawable(Context context, String name) {
        ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;

        if (Constants.BLANK.equalsIgnoreCase(name)) {
            int color2 = mColorGenerator.getColor(name);
            return TextDrawable.builder()
                    .beginConfig()
                    .width(Math.round(context.getResources().getDimension(R.dimen.dp67))) // width in px
                    .height(Math.round(context.getResources().getDimension(R.dimen.dp67))) // height in px
                    .endConfig()
                    .buildRound(("" + name).toUpperCase(), color2);
        } else {
            int color2 = mColorGenerator.getColor(name);
            return TextDrawable.builder()
                    .beginConfig()
                    .width(Math.round(context.getResources().getDimension(R.dimen.dp67))) // width in px
                    .height(Math.round(context.getResources().getDimension(R.dimen.dp67))) // height in px
                    .endConfig()
                    .buildRound(("" + name.charAt(0)).toUpperCase(), color2);
        }

    }

    public static int getVersionCode(Context mContext) {
        int versionCode = -1;
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            versionCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVersionName(Context mContext) {
        String versionName = "";
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    public static String getCurrentDateTime() // for enrollmentId
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);

        String Year = String.valueOf(year);
        StringBuffer dString = new StringBuffer();
        dString.append((date > 9) ? String.valueOf(date) : ("0" + date));
        dString.append("-");
        dString.append((month > 9) ? String.valueOf(month) : ("0" + month));
        dString.append("-");
        dString.append(year);
        return dString.toString();
    }

    public static String getCurrentDate(String yyyyMmDd) // for enrollmentId
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(yyyyMmDd, Locale.US);
        return df.format(c.getTime());
    }

    public static void showSnack(Context mContext, View mViewById, String msg) {
        Snackbar snack = Snackbar.make(mViewById, msg, Snackbar.LENGTH_SHORT);
        ViewGroup group = (ViewGroup) snack.getView();
        group.setBackgroundColor(mContext.getResources().getColor(R.color.errorColor));
        snack.show();
    }

    public static DateTime convertToDateTime(String stringToConvert, String currentFormat) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(currentFormat);
        return formatter.parseDateTime(stringToConvert);
    }

    public static String getCurrentTimeStamp(String expectedFormat) {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat(expectedFormat, Locale.US);
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    /**
     * Generate a value suitable for use in {#setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                Log.d("GENERATED_ID:", " " + result);
                return result;
            }
        }
    }

    public static void dateDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

    }


    public static String getCalculatedDate(String inFormat, int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, days);
        Date date = new Date(cal.getTimeInMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(inFormat, Locale.US);
        return dateFormat.format(date);
    }

    public static String stripExtension(final String s) {
        return s != null && s.lastIndexOf(".") > 0 ? s.substring(0, s.lastIndexOf(".")) : s;
    }

    public static String getDayFromDateTime(String dateText, String originalDateFormat, String expectedDateFormat, String expectedTimeFromat) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();

        DateFormat expectedDFormat = new SimpleDateFormat(expectedDateFormat, Locale.US);

        SimpleDateFormat expectedTFormat = null;
        if (expectedTimeFromat != null)
            expectedTFormat = new SimpleDateFormat(expectedTimeFromat, Locale.US);

        SimpleDateFormat originalFormat = new SimpleDateFormat(originalDateFormat, Locale.US);
        Date date;
        try {
            date = originalFormat.parse(dateText);
        } catch (ParseException ex) {
            return "";
        }

        String originalDateAsString = expectedDFormat.format(date);

        String todayAsString = expectedDFormat.format(today);
        String yesterdayAsString = expectedDFormat.format(yesterday);

        if (todayAsString.equals(originalDateAsString))
            return expectedTFormat == null ? "Today" : expectedTFormat.format(date);

        if (yesterdayAsString.equals(originalDateAsString))
            return "Yesterday";

        return originalDateAsString;
    }

    public static String getDayFromDate(String dateFormat, String date) {

        date = date.trim();
        Date currentDate = new Date();
        String timeString = new SimpleDateFormat(dateFormat + " HH:mm:ss", Locale.US).format(currentDate).substring(10);

        SimpleDateFormat mainDateFormat = new SimpleDateFormat(dateFormat + " HH:mm:ss", Locale.US);
        Date formattedInputDate = null;
        try {
            formattedInputDate = mainDateFormat.parse(date + timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date.trim().equalsIgnoreCase(new SimpleDateFormat(dateFormat, Locale.US).format(currentDate).trim())) {
            return "Today";
        }
        //-----------
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterdayDate = cal.getTime();
        String sDate = new SimpleDateFormat(dateFormat, Locale.US).format(yesterdayDate);
        try {
            yesterdayDate = mainDateFormat.parse(sDate + timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (formattedInputDate.getTime() == yesterdayDate.getTime()) {
            return "Yesterday";
        }
        //-----------
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        Date tomorrowDate = cal.getTime();
        sDate = new SimpleDateFormat(dateFormat, Locale.US).format(tomorrowDate);
        try {
            tomorrowDate = mainDateFormat.parse(sDate + timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (formattedInputDate.getTime() == tomorrowDate.getTime()) {
            return "Tomorrow";
        } else {
            DateFormat f = new SimpleDateFormat("EEEE", Locale.US);
            try {
                return f.format(formattedInputDate);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }


    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    public static void Log(String tag, String message) {
        Log.e(tag, "" + message);
    }


    public static String getFormattedDate(String strDate, String sourceFormat, String destinyFormat) {
        if (!strDate.equals("")) {

            SimpleDateFormat df;
            df = new SimpleDateFormat(sourceFormat, Locale.US);
            Date date;
            try {
                date = df.parse(strDate);
                df = new SimpleDateFormat(destinyFormat, Locale.US);
                return df.format(date);

            } catch (ParseException e) {
                return "";
            }
        } else return "";
    }

    /**
     * The method will return the date and time in requested format
     *
     * @param selectedDateTime to be converted to requested format
     * @param requestedFormat  the format in which the provided datetime needs to be changed
     * @param formatString     differentiate parameter to format date or time
     * @return formated date or time
     */
    public static String formatDateTime(String selectedDateTime, String requestedFormat, String currentDateFormat, String formatString) {


        if (formatString.equalsIgnoreCase(Constants.TIME)) {
            // SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_PATTERN.HH_MM, Locale.US);
            SimpleDateFormat ft = new SimpleDateFormat(currentDateFormat, Locale.US);

            Date dateObj = null;

            try {
                dateObj = ft.parse(selectedDateTime);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            long millis = dateObj.getTime();
            SimpleDateFormat simpleDateFormatObj = new SimpleDateFormat(requestedFormat, Locale.US);
            return simpleDateFormatObj.format(millis);

        }//if

        else if (formatString.equalsIgnoreCase(Constants.DATE)) {
            SimpleDateFormat ft = new SimpleDateFormat(currentDateFormat, Locale.US);

            Date dateObj = null;

            try {
                dateObj = ft.parse(selectedDateTime);

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            SimpleDateFormat simpleDateFormatObj = new SimpleDateFormat(requestedFormat, Locale.US);
            return simpleDateFormatObj.format(dateObj);


        }
        return null;

    }


    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int displayAgeAnalysis(DateTime dateToday, DateTime birthdayDate) {
        Period dateDifferencePeriod = displayBirthdayResult(dateToday, birthdayDate);
        int getDateInDays = dateDifferencePeriod.getDays();
        int getDateInWeeks = Weeks.weeksBetween(new DateTime(birthdayDate), new DateTime(dateToday)).getWeeks();
        ;
        int getDateInMonths = dateDifferencePeriod.getMonths();
        int getDateInYears = dateDifferencePeriod.getYears();
        int mDay = getDateInWeeks * 7;
        int mMonth = getDateInMonths + (getDateInYears * 12);
        int hours = mDay * 24;
        int minutes = mDay * 24 * 60;
        int seconds = mDay * 24 * 60 * 60;
        return getDateInYears;
    }

    public static Period displayBirthdayResult(DateTime dateToday, DateTime birthdayDate) {
        Period dateDifferencePeriod = new Period(birthdayDate, dateToday, PeriodType.yearMonthDayTime());
        return dateDifferencePeriod;
    }

    public static Date convertStringToDate(String dateString, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);
        Date date = null;

        try {
            date = formatter.parse(dateString.trim());
        } catch (ParseException e) {
            e.printStackTrace();
            CommonMethods.Log("convertStringToDate", "convertStringToDate EXCEPTION OCCURS : " + e.getMessage());
        }
        return date;
    }

    public static String getDateSelectedDoctorVisit(String visitdate, String dateFormat) {
        String yourDate = null;

        DateFormat format = new SimpleDateFormat(dateFormat, Locale.US);

        try {
            Date date = format.parse(visitdate);
            format = new SimpleDateFormat("d'th' MMM, yyyy", Locale.US);
            yourDate = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return yourDate;

    }


    public static String getSuffixForNumber(final int n) {
        //  checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }

    }

    public static String getExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    public static String getFileNameFromPath(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    public static String getFilePath(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("/") + 1);
    }

    public static float spToPx(int sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
    }


    private static boolean isValidIP(String ipAddr) {

        Pattern ptn = Pattern.compile("(\\b(1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\b)\\.(\\b(1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\b)\\.(\\b(1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\b)\\.(\\b(1?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\b)\\:(\\d{1,5})$");
        Matcher mtch = ptn.matcher(ipAddr);
        return mtch.find();
    }

    public static View loadView(int resourceName, Context mActivity) {

        LayoutInflater inflater = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // param.gravity = Gravity.CENTER;
        View child = inflater.inflate(resourceName, null);
        LinearLayout l1 = new LinearLayout(mActivity);
        child.setLayoutParams(param);

        l1.setLayoutParams(param);
        l1.addView(child);
        return l1;
    }


    public static File storeAndGetDocument(Context context, String base64Pdf, String filename, String extension) {
        // Create a file in the Internal Storage

        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = null;

        byte[] pdfAsBytes = Base64.decode(base64Pdf, 0);

        FileOutputStream outputStream;
        try {

            file = new File(filepath + "/Android/data/" + context.getPackageName() + "/Documents");
            if (!file.exists()) {
                file.mkdirs();
            }

            file = new File(file.getAbsolutePath(), filename + "." + extension);

            outputStream = new FileOutputStream(file);
            outputStream.write(pdfAsBytes);
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}

