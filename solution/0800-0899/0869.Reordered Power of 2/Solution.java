class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = convert(n);
        for (int i = 1; i <= Math.pow(10, 9); i <<= 1) {
            if (s.equals(convert(i))) {
                return true;
            }
        }
        return false;
    }

    private String convert(int n) {
        char[] cnt = new char[10];
        for (; n > 0; n /= 10) {
            cnt[n % 10]++;
        }
        return new String(cnt);
    }
}