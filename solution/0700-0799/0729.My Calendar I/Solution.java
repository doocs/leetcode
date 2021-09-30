class MyCalendar {
    List<int[]> calendar;

    MyCalendar() {
        calendar = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] item : calendar) {
            if (item[0] < end && item[1] > start) {
                return false;
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}