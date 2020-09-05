class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        if (arr.length < m * k) {
            return false;
        }
        for (int i = 0; i <= arr.length - m * k; i++) {
            boolean match = true;
            for (int j = i + m; j < i + m * k; j++) {
                if (arr[j] != arr[j - m]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }
}