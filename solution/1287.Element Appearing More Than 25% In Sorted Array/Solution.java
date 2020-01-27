class Solution {
    public int findSpecialInteger(int[] arr) {
        int total = arr.length;
        for (int i = 0; i < total; ++i) {
            if (arr[i] == arr[i + (total >> 2)]) {
                return arr[i];
            }
        }
        return 0;
    }
}