public class Solution {
    public int MinArray(int[] numbers) {
        int left = 0, right = numbers.Length - 1, mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            } else {
                right -= 1;
            }
        }
        return numbers[left];
    }
}