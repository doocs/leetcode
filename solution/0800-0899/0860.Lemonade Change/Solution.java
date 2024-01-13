class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int v : bills) {
            switch (v) {
                case 5 -> ++five;
                case 10 -> {
                    ++ten;
                    --five;
                }
                case 20 -> {
                    if (ten > 0) {
                        --ten;
                        --five;
                    } else {
                        five -= 3;
                    }
                }
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }
}