class Solution {
    public int longestMountain(int[] arr) {
        int left = 0, right = 0;
        int ans = 0;
        int status = -1;
        while (++right < arr.length) {
            if (status == -1 || status == 1) {
                if (arr[right] == arr[right - 1]) {
                    status = -1;
                }
                if (status == -1) {
                    if (arr[right] > arr[right - 1]) {
                        status = 1;
                    } else {
                        left = right;
                    }
                }
                if (status == 1 && arr[right] < arr[right - 1]) {
                    status = 2;
                }
            } else {
                if (arr[right] > arr[right - 1]) {
                    status = 1;
                    ans = Math.max(right - left, ans);
                    left = right - 1;
                } else if (arr[right] == arr[right - 1]) {
                    status = -1;
                    ans = Math.max(right - left, ans);
                    left = right;
                }
            }
        }
        if (status == 2) {
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}