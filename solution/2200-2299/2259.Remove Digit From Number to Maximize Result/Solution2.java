class Solution {
    public String removeDigit(String number, char digit) {
        int last = -1;
        int n = number.length();
        for (int i = 0; i < n; ++i) {
            char d = number.charAt(i);
            if (d == digit) {
                last = i;
                if (i + 1 < n && d < number.charAt(i + 1)) {
                    break;
                }
            }
        }
        return number.substring(0, last) + number.substring(last + 1);
    }
}