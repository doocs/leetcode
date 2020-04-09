class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) return false;
        int sum = 0;
        while (sum != n) {
            while (n > 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (sum == 1) return true;
            if (sum == 4) return false;
            n = sum;
            sum = 0;
        }
        return false;
    }
}


// 递归
class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) return false;
        int sum = 0;
        while (n > 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        if (sum == 1) return true;
        if (sum == 4) return false;
        return isHappy(sum);
    }
}