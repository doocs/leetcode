public class Solution {
    public int firstMissingPositive(int[] num) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0 && num[i] < num.length && num[num[i] - 1] != num[i]) {
                swap(num, i, num[i] - 1);
                i--;
            }
        }

        for (int i = 0; i < num.length; i++) {
            if (i + 1 != num[i]) {
                return i + 1;
            }
        }

        return num.length + 1;
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}