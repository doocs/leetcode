public class Solution {
    public int MinArray(int[] numbers) {
        int l = 0, r = numbers.Length - 1;
        while (l < r) {
            if (numbers[l] < numbers[r]) {
                break;
            }
            int m = (l + r) >> 1;
            if (numbers[m] > numbers[l]) {
                l = m + 1;
            } else if (numbers[m] < numbers[l]) {
                r = m;
            } else {
                ++l;
            }
        }
        return numbers[l];
    }
}
