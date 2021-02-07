class Solution {
    public boolean isStrobogrammatic(String num) {
        int n = num.length();
        for (int i = 0, j = n - 1; i <= j; ++i, --j) {
            if (!match(num.charAt(i), num.charAt(j))) return false;
        }
        return true;
    }

    private boolean match(char a, char b) {
        switch (a) {
            case '0':
            case '1':
            case '8':
                return a == b;
            case '6':
                return b == '9';
            case '9':
                return b == '6';
            default:
                return false;
        }
    }
}