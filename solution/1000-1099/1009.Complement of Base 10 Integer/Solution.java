class Solution {
    public int bitwiseComplement(int N) {
        int ans = 0;
        int index = -1;
        if (N == 0) return 1;
        if (N == 1) return 0;
        while (N / 2 != 0) {
            index++;
            int temp = N % 2 == 0 ? 1 : 0;
            if (temp == 1) {
                ans += Math.pow(2, index);
            }
            N /= 2;
        }
        return ans;
    }
}
