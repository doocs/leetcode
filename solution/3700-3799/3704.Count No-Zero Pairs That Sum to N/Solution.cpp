class Solution {
public:
    long long countNoZeroPairs(long long n) {
        std::string s = std::to_string(n);
        int m = (int) s.size();
        std::vector<int> digits(m + 1);
        for (int i = 0; i < m; i++) {
            digits[i] = s[m - 1 - i] - '0';
        }
        digits[m] = 0;

        long long dp[2][2][2] = {};
        dp[0][1][1] = 1;

        for (int pos = 0; pos < m + 1; pos++) {
            long long ndp[2][2][2] = {};
            int target = digits[pos];
            for (int carry = 0; carry <= 1; carry++) {
                for (int aliveA = 0; aliveA <= 1; aliveA++) {
                    for (int aliveB = 0; aliveB <= 1; aliveB++) {
                        long long ways = dp[carry][aliveA][aliveB];
                        if (!ways) continue;
                        int aDigits[10];
                        int aNext[10];
                        int aLen = 0;
                        if (aliveA) {
                            for (int d = 1; d <= 9; d++) {
                                aDigits[aLen] = d;
                                aNext[aLen] = 1;
                                aLen++;
                            }
                            if (pos > 0) {
                                aDigits[aLen] = 0;
                                aNext[aLen] = 0;
                                aLen++;
                            }
                        } else {
                            aDigits[0] = 0;
                            aNext[0] = 0;
                            aLen = 1;
                        }

                        int bDigits[10];
                        int bNext[10];
                        int bLen = 0;
                        if (aliveB) {
                            for (int d = 1; d <= 9; d++) {
                                bDigits[bLen] = d;
                                bNext[bLen] = 1;
                                bLen++;
                            }
                            if (pos > 0) {
                                bDigits[bLen] = 0;
                                bNext[bLen] = 0;
                                bLen++;
                            }
                        } else {
                            bDigits[0] = 0;
                            bNext[0] = 0;
                            bLen = 1;
                        }

                        for (int ia = 0; ia < aLen; ia++) {
                            int da = aDigits[ia];
                            int na = aNext[ia];
                            for (int ib = 0; ib < bLen; ib++) {
                                int db = bDigits[ib];
                                int nb = bNext[ib];
                                int sum = da + db + carry;
                                if (sum % 10 != target) continue;
                                int ncarry = sum / 10;
                                ndp[ncarry][na][nb] += ways;
                            }
                        }
                    }
                }
            }
            std::memcpy(dp, ndp, sizeof(dp));
        }

        return dp[0][0][0];
    }

    long long countPairs(long long n) {
        return countNoZeroPairs(n);
    }
};
