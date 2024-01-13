class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {heights[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[arr[i][1]];
        }
        return ans;
    }
}