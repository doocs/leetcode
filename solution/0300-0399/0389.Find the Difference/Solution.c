char findTheDifference(char* s, char* t) {
    int n = strlen(s);
    int cnt[26] = {0};
    for (int i = 0; i < n; i++) {
        cnt[s[i] - 'a']++;
        cnt[t[i] - 'a']--;
    }
    cnt[t[n] - 'a']--;
    for (int i = 0;; i++) {
        if (cnt[i]) {
            return 'a' + i;
        }
    }
}