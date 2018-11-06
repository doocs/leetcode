/*
class Solution {
    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        
        long tmp = x;
        boolean isPositive = true;
        if (tmp < 0) {
            isPositive = false;
            tmp = -tmp;
        }
        
        long val = Long.parseLong(new StringBuilder(String.valueOf(tmp)).reverse().toString());
        
        return isPositive ? (val > Integer.MAX_VALUE ? 0 : (int) val) : (-val < Integer.MIN_VALUE ? 0 : (int) (-val)); 
        
    }
}
*/

class Solution {
    public int reverse(int x) {
        long res = 0;
        // 考虑负数情况，所以这里条件为: x != 0
        while (x != 0) {
            res = res * 10 + (x % 10);
            x /= 10;
        }
        return (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) 
            ? 0
            : (int) res;
            
    }
}