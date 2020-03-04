class Solution {
    public int minArray(int[] numbers) {
        int len = numbers.length;
        if (len == 1 || numbers[0] < numbers[len - 1]) {
            return numbers[0];
        }

        int left = 0, right = len - 1;
        while (right - left > 1) {
            int mid = left + ((right - left) >> 1);
            if (numbers[left] == numbers[mid] && numbers[mid] == numbers[right]) {
                return findMin(numbers, left, right);
            }
            if (numbers[mid] >= numbers[left]) {
                left = mid;
            } else if (numbers[mid] <= numbers[right]) {
                right = mid;
            }
        }
        return numbers[right];
    }

    private int findMin(int[] numbers, int left, int right) {
        int min = numbers[left];
        for (int i = left + 1; i < right; ++i) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }
}