class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;
        for (int a : arr1) {
            if (check(arr2, a, d)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int[] arr, int a, int d) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] >= a - d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l >= arr.length || arr[l] > a + d;
    }
}