class Solution {
    public boolean reorderedPowerOf2(int n) {
        String s = convert(n);
        for (int i = 1; i <= Math.pow(10, 9); i <<= 1) {
            if (s.equals(convert(i))) {
                return true;
            }
        }
        return false;
    }

    private String convert(int n) {
        char[] counter = new char[10];
        while (n > 0) {
            ++counter[n % 10];
            n /= 10;
        }
        return new String(counter);
    }
}