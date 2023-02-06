/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* executeInstructions(int n, int* startPos, int startPosSize, char* s, int* returnSize) {
    int m = strlen(s);
    int* ans = malloc(sizeof(int) * m);
    for (int i = 0; i < m; i++) {
        int y = startPos[0];
        int x = startPos[1];
        int j = i;
        for (j = i; j < m; j++) {
            if (s[j] == 'U') {
                y--;
            } else if (s[j] == 'D') {
                y++;
            } else if (s[j] == 'L') {
                x--;
            } else {
                x++;
            }
            if (y == -1 || y == n || x == -1 || x == n) {
                break;
            }
        }
        ans[i] = j - i;
    }
    *returnSize = m;
    return ans;
}
