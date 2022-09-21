class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {aliceValues[i] + bobValues[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int j = arr[i][1];
            if (i % 2 == 0) {
                a += aliceValues[j];
            } else {
                b += bobValues[j];
            }
        }
        if (a == b) {
            return 0;
        }
        return a > b ? 1 : -1;
    }
}