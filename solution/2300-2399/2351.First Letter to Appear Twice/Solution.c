char repeatedCharacter(char* s) {
    int vis[26] = {0};
    for (int i = 0; s[i]; i++) {
        if (vis[s[i] - 'a']) {
            return s[i];
        }
        vis[s[i] - 'a']++;
    }
    return ' ';
}