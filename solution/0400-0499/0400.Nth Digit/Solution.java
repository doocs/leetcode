class Solution {
    public int findNthDigit(int n) {
        /***
         * 12345678910111213
         * 规律个位数9个数一共有9*1,两位数90个数 一共有90*2个数字,三位数有900个数一共有900*3个数字,以此类推
         * 举例15,15-9=6,6/2=3...0,余数是0,那么这个数值value=10*(2-1)+(3-1)=12,整除取最后一位  12%10=2
         * 举例14,14-9=5,5/2=2...1,余数不为0,那么这个数值value=10*(2-1)+2=12,则为这个数的第余数个 12/(10*(2-1))%10=1
         */
        long max = 9;
        long num = n;
        long digits = 1;//是几位数
        while (n > 0) {
            if (num - max * size > 0) {
                num = num - max * size;
                digits++;
                max = max * 10;
            } else {
                long count = num / digits;
                long childDigits = num % digits;
                if (childDigits == 0) {
                    return (int) (((long) Math.pow(10, digits - 1) + count - 1) % 10);
                } else {
                    return (int) (((long) Math.pow(10, digits - 1) + count) / ((long) Math.pow(10, (digits - childDigits))) % 10);
                }
            }
        }
        return 0;

    }
}