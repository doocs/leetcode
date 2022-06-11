class Solution {
    public int[] threeEqualParts(int[] arr) {
        int n = arr.length;
        int cnt1 = 0;
        for (int v : arr) {
            cnt1 += v;
        }
        int cnt = cnt1 / 3;
        int mod = cnt1 % 3;
        if (mod != 0) {
            return new int[]{-1, -1};
        }
        if (cnt == 0) {
            return new int[]{0, n - 1};
        }
        int i = find(arr, 1);
        int j = find(arr, cnt + 1);
        int k = find(arr, cnt * 2 + 1);
        while (k < n && arr[i] == arr[j] && arr[j] == arr[k]) {
            ++i;
            ++j;
            ++k;
        }
        if (k == n) {
            return new int[]{i - 1, j};
        }
        return new int[]{-1, -1};
    }

    private int find(int[] arr, int cnt) {
        int s = 0;
        for (int i = 0; i < arr.length; ++i) {
            s += arr[i];
            if (s == cnt) {
                return i;
            }
        }
        return -1;
    }
}