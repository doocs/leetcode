int countMatches(char*** items, int itemsSize, int* itemsColSize, char* ruleKey, char* ruleValue) {
    int k = strcmp(ruleKey, "type") == 0 ? 0 : strcmp(ruleKey, "color") == 0 ? 1
                                                                             : 2;
    int res = 0;
    for (int i = 0; i < itemsSize; i++) {
        if (strcmp(items[i][k], ruleValue) == 0) {
            res++;
        }
    }
    return res;
}
