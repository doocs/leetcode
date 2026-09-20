class Solution {
    private static final int[][] DIGIT_PRIMES = {
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {2, 0, 0, 0},
        {0, 0, 1, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 1},
        {3, 0, 0, 0},
        {0, 2, 0, 0},
    };

    public String smallestNumber(String num, long t) {
        int[] required = new int[4];
        if (!factorize(t, required)) {
            return "-1";
        }
        int[] need = toDigits(required);
        if (sum(need) > num.length()) {
            return construct(need);
        }

        int[] prefix = new int[4];
        for (int i = 0; i < num.length(); ++i) {
            add(prefix, DIGIT_PRIMES[num.charAt(i) - '0']);
        }
        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            firstZero = num.length();
            if (isSubset(required, prefix)) {
                return num;
            }
        }

        int n = num.length();
        for (int i = n - 1; i >= 0; --i) {
            subtractInPlace(prefix, DIGIT_PRIMES[num.charAt(i) - '0']);
            int space = n - 1 - i;
            if (i > firstZero) {
                continue;
            }
            for (int bigger = num.charAt(i) - '0' + 1; bigger < 10; ++bigger) {
                int[] suffix = toDigits(subtract(subtract(required, prefix), DIGIT_PRIMES[bigger]));
                if (sum(suffix) <= space) {
                    StringBuilder ans = new StringBuilder();
                    ans.append(num, 0, i);
                    ans.append(bigger);
                    ans.append("1".repeat(space - sum(suffix)));
                    ans.append(construct(suffix));
                    return ans.toString();
                }
            }
        }
        int[] ext = toDigits(required);
        return "1".repeat(n + 1 - sum(ext)) + construct(ext);
    }

    private boolean factorize(long t, int[] counts) {
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; ++i) {
            while (t % primes[i] == 0) {
                t /= primes[i];
                ++counts[i];
            }
        }
        return t == 1;
    }

    private int[] toDigits(int[] primes) {
        int count8 = primes[0] / 3;
        int remaining2 = primes[0] % 3;
        int count9 = primes[1] / 2;
        int count3 = primes[1] % 2;
        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;
        int count6 = 0;
        if (count2 == 1 && count3 == 1) {
            count2 = 0;
            count3 = 0;
            count6 = 1;
        }
        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0;
            count4 = 0;
        }
        return new int[] {
            0, 0, count2, count3, count4, primes[2], count6, primes[3], count8, count9};
    }

    private String construct(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int d = 2; d < 10; ++d) {
            sb.append(String.valueOf(d).repeat(digits[d]));
        }
        return sb.toString();
    }

    private boolean isSubset(int[] a, int[] b) {
        for (int i = 0; i < a.length; ++i) {
            if (b[i] < a[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] subtract(int[] a, int[] b) {
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = Math.max(0, a[i] - b[i]);
        }
        return res;
    }

    private void subtractInPlace(int[] a, int[] b) {
        for (int i = 0; i < a.length; ++i) {
            a[i] = Math.max(0, a[i] - b[i]);
        }
    }

    private void add(int[] a, int[] b) {
        for (int i = 0; i < a.length; ++i) {
            a[i] += b[i];
        }
    }

    private int sum(int[] a) {
        int s = 0;
        for (int v : a) {
            s += v;
        }
        return s;
    }
}
