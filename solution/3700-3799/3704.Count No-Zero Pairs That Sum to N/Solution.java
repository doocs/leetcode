class Solution {
    public long countNoZeroPairs(long n) {
        char[] cs = Long.toString(n).toCharArray();
        int m = cs.length;
        int[] digits = new int[m + 1];
        for (int i = 0; i < m; i++) {
            digits[i] = cs[m - 1 - i] - '0';
        }
        digits[m] = 0;

        long[][][] dp = new long[2][2][2];
        dp[0][1][1] = 1;

        for (int pos = 0; pos < m + 1; pos++) {
            long[][][] ndp = new long[2][2][2];
            int target = digits[pos];
            for (int carry = 0; carry <= 1; carry++) {
                for (int aliveA = 0; aliveA <= 1; aliveA++) {
                    for (int aliveB = 0; aliveB <= 1; aliveB++) {
                        long ways = dp[carry][aliveA][aliveB];
                        if (ways == 0) {
                            continue;
                        }
                        int[] aDigits;
                        int[] aNext;
                        if (aliveA == 1) {
                            if (pos == 0) {
                                aDigits = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
                                aNext = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1};
                            } else {
                                aDigits = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
                                aNext = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
                            }
                        } else {
                            aDigits = new int[] {0};
                            aNext = new int[] {0};
                        }

                        int[] bDigits;
                        int[] bNext;
                        if (aliveB == 1) {
                            if (pos == 0) {
                                bDigits = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
                                bNext = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1};
                            } else {
                                bDigits = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
                                bNext = new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 0};
                            }
                        } else {
                            bDigits = new int[] {0};
                            bNext = new int[] {0};
                        }

                        for (int ai = 0; ai < aDigits.length; ai++) {
                            int da = aDigits[ai];
                            int na = aNext[ai];
                            for (int bi = 0; bi < bDigits.length; bi++) {
                                int db = bDigits[bi];
                                int nb = bNext[bi];
                                int s = da + db + carry;
                                if (s % 10 != target) {
                                    continue;
                                }
                                int ncarry = s / 10;
                                ndp[ncarry][na][nb] += ways;
                            }
                        }
                    }
                }
            }
            dp = ndp;
        }

        return dp[0][0][0];
    }

    public long countPairs(long n) {
        return countNoZeroPairs(n);
    }
}
