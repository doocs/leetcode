class MyCalendarTwo {
    List<int[]> calendar;
    List<int[]> duplicationList;

    MyCalendarTwo() {
        calendar = new ArrayList<>();
        duplicationList = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] item : duplicationList) {
            if (item[0] < end && item[1] > start) {
                return false;
            }
        }
        for (int[] item : calendar) {
            if (item[0] < end && item[1] > start) {
                duplicationList.add(new int[]{Math.max(start, item[0]), Math.min(end, item[1])});
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}
