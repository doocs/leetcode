public class Solution {
    public int MinArray(int[] numbers) {
        int l = 0, r = numbers.Length - 1;
        while (l < r) {
            int m = (l + r) >> 1;
            if (numbers[m] > numbers[r]) {
                l = m + 1;
            } else if (numbers[m] < numbers[r]) {
                r = m;
            } else {
                --r;
            }
        }
        return numbers[l];
    }
}
