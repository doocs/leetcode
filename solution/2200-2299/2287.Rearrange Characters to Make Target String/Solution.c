#define min(a, b) (((a) < (b)) ? (a) : (b))

int rearrangeCharacters(char* s, char* target) {
    int count1[26] = {0};
    int count2[26] = {0};
    for (int i = 0; s[i]; i++) {
        count1[s[i] - 'a']++;
    }
    for (int i = 0; target[i]; i++) {
        count2[target[i] - 'a']++;
    }
    int ans = INT_MAX;
    for (int i = 0; i < 26; i++) {
        if (count2[i]) {
            ans = min(ans, count1[i] / count2[i]);
        }
    }
    return ans;
}
