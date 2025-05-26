int lengthOfLongestSubstring(char* s) {
    int freq[256] = {0};
    int l = 0, r = 0;
    int ans = 0;
    int len = strlen(s);

    for (r = 0; r < len; r++) {
        char c = s[r];
        freq[(unsigned char) c]++;

        while (freq[(unsigned char) c] > 1) {
            freq[(unsigned char) s[l]]--;
            l++;
        }

        if (ans < r - l + 1) {
            ans = r - l + 1;
        }
    }

    return ans;
}
