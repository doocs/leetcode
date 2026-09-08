class Solution {
    public String largestTimeFromDigits(int[] arr) {
        int ans = -1;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i != j && j != k && i != k) {
                        int h = arr[i] * 10 + arr[j];
                        int m = arr[k] * 10 + arr[6 - i - j - k];
                        if (h < 24 && m < 60) {
                            ans = Math.max(ans, h * 60 + m);
                        }
                    }
                }
            }
        }
        return ans < 0 ? "" : String.format("%02d:%02d", ans / 60, ans % 60);
    }
}