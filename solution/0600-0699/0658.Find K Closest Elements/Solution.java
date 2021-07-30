class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if (arr.length < k) {
            for (int item : arr) {
                res.add(item);
            }
            return res;
        }
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (arr[mid] > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        int left1 = 0;
        int right1 = arr.length - 1;
        if (left >= k) {
            left1 = left - k;
        }
        if (arr.length - 1 - left >= k) {
            right1 = left + k;
        }
        while (right1 - left1 >= k) {
            if (Math.abs(arr[left1] - x) > Math.abs(arr[right1] -x)) {
                left1++;
            } else {
                right1--;
            }
        }
        while (left1 <= right1) {
            res.add(arr[left1]);
            left1++;
        }
        return res;
    }
}