class Solution {
    public int countDigitOne(int n) {
        if (n < 1) {
            return 0;
        }
        String s = String.valueOf(n);
        int high = s.charAt(0) - '0'; // 最高位
        int base = (int) Math.pow(10, s.length() - 1); // 基数
        int lows = n % base; // 低位
        return high == 1
            ? countDigitOne(base - 1) + countDigitOne(lows) + lows + 1
            : high * countDigitOne(base - 1) + countDigitOne(lows) + base;
    }
}