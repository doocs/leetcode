class Solution {
    public int getWinner(int[] arr, int k) {
        int time = 0, max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max > arr[i]) {
                time++;
            } else {
                time = 1;
                max = arr[i];
            }
            if (time >= k) {
                return max;
            }
        }
        return max;
    }
}