class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, j = numbers.length - 1;;) {
            int x = numbers[i] + numbers[j];
            if (x == target) {
                return new int[] {i, j};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
    }
}