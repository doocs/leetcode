class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char ans = keysPressed.charAt(0);
        int mx = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; ++i) {
            int d = releaseTimes[i] - releaseTimes[i - 1];
            if (d > mx || (d == mx && keysPressed.charAt(i) > ans)) {
                mx = d;
                ans = keysPressed.charAt(i);
            }
        }
        return ans;
    }
}