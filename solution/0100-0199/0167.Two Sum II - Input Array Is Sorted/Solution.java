class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, n = numbers.length;; ++i) {
            int x = target - numbers[i];
            int l = i + 1, r = n - 1;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (numbers[mid] >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (numbers[l] == x) {
                return new int[] {i + 1, l + 1};
            }
        }
    }
}