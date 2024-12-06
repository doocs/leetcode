class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int s = Arrays.stream(arr).sum();
        if (s % 3 != 0) {
            return false;
        }
        s /= 3;
        int cnt = 0, t = 0;
        for (int x : arr) {
            t += x;
            if (t == s) {
                cnt++;
                t = 0;
            }
        }
        return cnt >= 3;
    }
}
