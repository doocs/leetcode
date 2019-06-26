class Solution {
    public boolean lemonadeChange(int[] bills) {
        int fives = 0, tens = 0;
        for (int bill : bills) {
            if (bill == 5) {
                ++fives;
            } else if (bill == 10) {
                ++tens;
                if (--fives < 0) {
                    return false;
                }
            } else {
                if (tens >= 1 && fives >= 1) {
                    --tens;
                    --fives;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
