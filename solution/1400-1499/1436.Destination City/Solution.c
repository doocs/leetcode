char* destCity(char*** paths, int pathsSize, int* pathsColSize) {
    for (int i = 0; i < pathsSize; i++) {
        int flag = 1;
        for (int j = 0; j < pathsSize; j++) {
            if (strcmp(paths[i][1], paths[j][0]) == 0) {
                flag = 0;
                break;
            }
        }
        if (flag) {
            return paths[i][1];
        }
    }
    return NULL;
}
