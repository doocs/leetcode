class Solution {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int[] c = new int[1010];
        for (int i = 0; i < startTime.length; ++i) {
            c[startTime[i]]++;
            c[endTime[i] + 1]--;
        }
        int ans = 0;
        for (int i = 0; i <= queryTime; ++i) {
            ans += c[i];
        }
        return ans;
    }
}