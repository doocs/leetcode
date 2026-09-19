class Solution {
    private static final int LIMIT = 1_000_001;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++freq[s.charAt(i) - 'a'];
        }
        int odd = 0;
        int mid = 26;
        for (int i = 0; i < 26; ++i) {
            if ((freq[i] & 1) == 1) {
                ++odd;
                mid = i;
            }
        }
        if (odd > 1) {
            return "";
        }
        int[] half = new int[26];
        int halfLen = 0;
        for (int i = 0; i < 26; ++i) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }
        if (k > countPermutations(half)) {
            return "";
        }
        char[] pal = new char[n];
        int rank = k;
        int pos = 0;
        for (int t = 0; t < halfLen; ++t) {
            for (int i = 0; i < 26; ++i) {
                if (half[i] == 0) {
                    continue;
                }
                --half[i];
                int suffix = countPermutations(half);
                if (suffix >= rank) {
                    pal[pos++] = (char) ('a' + i);
                    break;
                }
                rank -= suffix;
                ++half[i];
            }
        }
        if (mid < 26) {
            pal[halfLen] = (char) ('a' + mid);
        }
        for (int i = 0; i < halfLen; ++i) {
            pal[n - 1 - i] = pal[i];
        }
        return new String(pal);
    }

    private int countPermutations(int[] counts) {
        int remaining = 0;
        for (int c : counts) {
            remaining += c;
        }
        long perms = 1;
        for (int count : counts) {
            if (count == 0) {
                continue;
            }
            int selected = Math.min(count, remaining - count);
            long combos = 1;
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
}
