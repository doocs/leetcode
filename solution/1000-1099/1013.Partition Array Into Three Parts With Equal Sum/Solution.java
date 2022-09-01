class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int s = 0;
        for (int v : arr) {
            s += v;
        }
        if (s % 3 != 0) {
            return false;
        }
        int i = 0, j = arr.length - 1;
        int a = 0, b = 0;
        while (i < arr.length) {
            a += arr[i];
            if (a == s / 3) {
                break;
            }
            ++i;
        }
        while (j >= 0) {
            b += arr[j];
            if (b == s / 3) {
                break;
            }
            --j;
        }
        return i < j - 1;
    }
}