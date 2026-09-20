class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; ++i) {
            ++freq[s.charAt(i) - 'a'];
        }
        int odd = 0;
        char middle = 0;
        for (int i = 0; i < 26; ++i) {
            if (freq[i] % 2 == 1) {
                ++odd;
                middle = (char) ('a' + i);
            }
        }
        if (odd > 1) {
            return "";
        }
        int[] half = new int[26];
        for (int i = 0; i < 26; ++i) {
            half[i] = freq[i] / 2;
        }
        int halfLen = n / 2;
        String targetHalf = target.substring(0, halfLen);
        int[] remaining = half.clone();
        StringBuilder prefix = new StringBuilder();
        int matched = 0;
        for (int i = 0; i < halfLen; ++i) {
            int x = targetHalf.charAt(i) - 'a';
            if (remaining[x] == 0) {
                break;
            }
            prefix.append(targetHalf.charAt(i));
            --remaining[x];
            ++matched;
        }
        if (matched == halfLen) {
            String cand = build(prefix.toString(), middle, n);
            if (cand.compareTo(target) > 0) {
                return cand;
            }
        }
        int last = matched == halfLen ? halfLen - 1 : matched;
        for (int pos = last; pos >= 0; --pos) {
            int[] rem = half.clone();
            boolean valid = true;
            for (int i = 0; i < pos; ++i) {
                int x = targetHalf.charAt(i) - 'a';
                if (rem[x] == 0) {
                    valid = false;
                    break;
                }
                --rem[x];
            }
            if (!valid) {
                continue;
            }
            int targetChar = targetHalf.charAt(pos) - 'a';
            for (int c = targetChar + 1; c < 26; ++c) {
                if (rem[c] == 0) {
                    continue;
                }
                StringBuilder left = new StringBuilder(targetHalf.substring(0, pos));
                left.append((char) ('a' + c));
                --rem[c];
                for (int x = 0; x < 26; ++x) {
                    while (rem[x] > 0) {
                        left.append((char) ('a' + x));
                        --rem[x];
                    }
                }
                String cand = build(left.toString(), middle, n);
                if (cand.compareTo(target) > 0) {
                    return cand;
                }
                rem = half.clone();
                for (int i = 0; i < pos; ++i) {
                    --rem[targetHalf.charAt(i) - 'a'];
                }
            }
        }
        return "";
    }

    private String build(String left, char middle, int n) {
        String right = new StringBuilder(left).reverse().toString();
        if (n % 2 == 1) {
            return left + middle + right;
        }
        return left + right;
    }
}
