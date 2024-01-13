class Solution {
    public boolean checkIfExist(int[] arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v == 0) {
                ++cnt;
                if (cnt > 1) {
                    return true;
                }
            }
        }
        Arrays.sort(arr);
        for (int v : arr) {
            if (v != 0) {
                int idx = Arrays.binarySearch(arr, v * 2);
                if (idx >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}