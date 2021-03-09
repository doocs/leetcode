class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low <= high) {
            if (numbers[low] + numbers[high] == target) {
                return new int[]{low + 1, high + 1};
            }
            if (numbers[low] + numbers[high] < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}