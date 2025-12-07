class Solution {
    public boolean completePrime(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int x = 0;
        for (int i = 0; i < s.length; i++) {
            x = x * 10 + (s[i] - '0');
            if (!isPrime(x)) {
                return false;
            }
        }
        x = 0;
        int p = 1;
        for (int i = s.length - 1; i >= 0; i--) {
            x = p * (s[i] - '0') + x;
            p *= 10;
            if (!isPrime(x)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrime(int x) {
        if (x < 2) {
            return false;
        }
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
