class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        List<Integer> mins = new ArrayList<>();
        for (String t : timePoints) {
            String[] time = t.split(":");
            mins.add(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }
        Collections.sort(mins);
        mins.add(mins.get(0) + 24 * 60);
        int ans = 1 << 30;
        for (int i = 1; i < mins.size(); ++i) {
            ans = Math.min(ans, mins.get(i) - mins.get(i - 1));
        }
        return ans;
    }
}