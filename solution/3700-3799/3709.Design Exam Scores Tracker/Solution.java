class ExamTracker {
    private List<Integer> times = new ArrayList<>();
    private List<Long> pre = new ArrayList<>();

    public ExamTracker() {
        times.add(0);
        pre.add(0L);
    }

    public void record(int time, int score) {
        times.add(time);
        pre.add(pre.getLast() + score);
    }

    public long totalScore(int startTime, int endTime) {
        int l = binarySearch(startTime) - 1;
        int r = binarySearch(endTime + 1) - 1;
        return pre.get(r) - pre.get(l);
    }

    private int binarySearch(int x) {
        int l = 0, r = times.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (times.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */
