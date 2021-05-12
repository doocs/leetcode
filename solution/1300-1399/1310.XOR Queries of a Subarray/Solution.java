class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] preXor = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; ++i) {
            preXor[i] = preXor[i - 1] ^ arr[i - 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int l = queries[i][0], r = queries[i][1];
            res[i] = preXor[l] ^ preXor[r + 1];
        }
        return res;
    }
}