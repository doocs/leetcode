class Solution {
public:
    string smallestPalindrome(string s, int k) {
        int freq[26]{};
        int n = s.size();
        for (char c : s) {
            ++freq[c - 'a'];
        }
        int odd = 0, mid = 26;
        for (int i = 0; i < 26; ++i) {
            if (freq[i] & 1) {
                ++odd;
                mid = i;
            }
        }
        if (odd > 1) {
            return "";
        }
        int half[26]{};
        int halfLen = 0;
        for (int i = 0; i < 26; ++i) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }
        if (k > countPermutations(half)) {
            return "";
        }
        string pal(n, 0);
        int rank = k, pos = 0;
        for (int t = 0; t < halfLen; ++t) {
            for (int i = 0; i < 26; ++i) {
                if (half[i] == 0) {
                    continue;
                }
                --half[i];
                int suffix = countPermutations(half);
                if (suffix >= rank) {
                    pal[pos++] = char('a' + i);
                    break;
                }
                rank -= suffix;
                ++half[i];
            }
        }
        if (mid < 26) {
            pal[halfLen] = char('a' + mid);
        }
        for (int i = 0; i < halfLen; ++i) {
            pal[n - 1 - i] = pal[i];
        }
        return pal;
    }

private:
    static constexpr int LIMIT = 1000001;

    int countPermutations(const int counts[26]) {
        int remaining = 0;
        for (int i = 0; i < 26; ++i) {
            remaining += counts[i];
        }
        long long perms = 1;
        for (int i = 0; i < 26; ++i) {
            int count = counts[i];
            if (count == 0) {
                continue;
            }
            int selected = min(count, remaining - count);
            long long combos = 1;
            for (int step = 1; step <= selected; ++step) {
                combos = combos * (remaining - step + 1) / step;
                if (combos >= LIMIT) {
                    combos = LIMIT;
                    break;
                }
            }
            perms *= combos;
            if (perms >= LIMIT) {
                return LIMIT;
            }
            remaining -= count;
        }
        return (int) perms;
    }
};
