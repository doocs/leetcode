class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int v : bills) {
            if (v == 5) {
                ++five;
            } else if (v == 10) {
                ++ten;
                --five;
            } else {
                if (ten > 0) {
                    --ten;
                    --five;
                } else {
                    five -= 3;
                }
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }
}