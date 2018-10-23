class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == 0 || divisor == 1) {
            return dividend;
        }
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        // 商的符号，true 为正，false 为负
        boolean flag = true;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            flag = false;
        }
        long dividendLong = Math.abs((long)dividend);
        long divisorLong = Math.abs((long)divisor);

        int re = 0;
        long factor = 0x1;

        while (dividendLong >= (divisorLong << 1)) {
            divisorLong <<= 1;
            factor <<= 1;
        }

        while (factor > 0 && dividendLong > 0) {
            if(dividendLong >= divisorLong) {
                dividendLong -= divisorLong;
                re += factor;
            }
            factor >>>= 1;
            divisorLong >>>= 1;
        }

        return flag ? re : -re;
    }
}