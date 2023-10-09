class Solution {
    public int search(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (arr[l] == arr[r]) {
            --r;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] > arr[r]) {
                if (arr[l] <= target && target <= arr[mid]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                --r;
            }
        }
        return arr[l] == target ? l : -1;
    }
}