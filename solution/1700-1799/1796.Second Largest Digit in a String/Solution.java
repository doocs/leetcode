class Solution {
    public int secondHighest(String s) {
        int largestDigit = -1, secondLargestDigit = -1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int num = c - '0';
                if (num > largestDigit) {
                    secondLargestDigit = largestDigit;
                    largestDigit = num;
                } else if (num > secondLargestDigit && num < largestDigit) {
                    secondLargestDigit = num;
                }
            }
        }
        return secondLargestDigit;
    }
}