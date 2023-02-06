#define min(a, b) (((a) < (b)) ? (a) : (b))

bool isAlienSorted(char** words, int wordsSize, char* order) {
    int map[26] = {0};
    for (int i = 0; i < 26; i++) {
        map[order[i] - 'a'] = i;
    }
    for (int i = 1; i < wordsSize; i++) {
        char* s1 = words[i - 1];
        char* s2 = words[i];
        int n = strlen(s1);
        int m = strlen(s2);
        int len = min(n, m);
        int isEqual = 1;
        for (int j = 0; j < len; j++) {
            if (map[s1[j] - 'a'] > map[s2[j] - 'a']) {
                return 0;
            }
            if (map[s1[j] - 'a'] < map[s2[j] - 'a']) {
                isEqual = 0;
                break;
            }
        }
        if (isEqual && n > m) {
            return 0;
        }
    }
    return 1;
}
