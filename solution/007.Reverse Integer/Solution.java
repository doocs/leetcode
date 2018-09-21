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