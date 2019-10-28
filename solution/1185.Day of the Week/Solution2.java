class Solution {

    private final String[] week = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public String dayOfTheWeek(int day, int month, int year) {
        // Zeller Formula
        if(month == 1 || month == 2) {
            month += 12;
            year -= 1;
        }
        return week[(day +2*month+3*(month+1)/5+year+year/4-year/100+year/400+1) % 7];
    }
}