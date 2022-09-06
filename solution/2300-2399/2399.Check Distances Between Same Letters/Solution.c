bool checkDistances(char* s, int* distance, int distanceSize) {
    int n = strlen(s);
    int d[26] = {0};
    for (int i = 0; i < n; i++) {
        int j = s[i] - 'a';
        if (d[j] > 0 && i - d[j] != distance[j]) {
            return false;
        }
        d[j] = i + 1;
    }
    return true;
}
