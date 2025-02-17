class Solution {
    public int findSpecialInteger(int[] arr) {
        for (int i = 0;; ++i) {
            if (arr[i] == (arr[i + (arr.length >> 2)])) {
                return arr[i];
            }
        }
    }
}
