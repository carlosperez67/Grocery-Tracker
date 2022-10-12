//package model;
//
//public class Date {
//    int month;
//    int day;
//    int year;
//
//    // Requires: month in [1,12], day in [1,31],
//    // - format of MONTH,DATE,YEAR . example : 10,31,2022
//    // Modifies: This
//    // Effects: Constructs data with given month day and year
//    public Date(int month, int day, int year) {
//        this.month = month;
//        this.day = day;
//        this.year = year;
//    }
//
//    //getters
//    String getShortDate() {
//        return String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year);
//    }
//
//    // Effects: Coverts the month number to the English name of month
//    public String getMonthName() {
//        Months monthText;
//        if (month == 1) {
//            monthText = Months.January;
//        } else if (month == 2) {
//            monthText = Months.February;
//        } else if (month == 3) {
//            monthText = Months.March;
//        } else if (month == 4) {
//            monthText = Months.April;
//        } else if (month == 5) {
//            monthText = Months.May;
//        } else if (month == 6) {
//            monthText = Months.June;
//        } else if (month == 7) {
//            monthText = Months.July;
//        } else if (month == 8) {
//            monthText = Months.August;
//        } else if (month == 9) {
//            monthText = Months.September;
//        } else if (month == 10) {
//            monthText = Months.October;
//        } else if (month == 11) {
//            monthText = Months.November;
//        } else {
//            monthText = Months.December;
//        }
//        return monthText.name();
//    }
//
//    // Requires:
//    // Modifies:
//    // Effects: Returns date in form Month Day, Year
//    String getLongDate() {
//        return getMonthName() + String.valueOf(day) + ", " + String.valueOf(year);
//    }
//}