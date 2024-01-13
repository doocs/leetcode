class Solution {
    public int countTestedDevices(int[] batteryPercentages) {
        int ans = 0;
        for (int x : batteryPercentages) {
            x -= ans;
            if (x > 0) {
                ++ans;
            }
        }
        return ans;
    }
}