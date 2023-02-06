/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
double* convertTemperature(double celsius, int* returnSize) {
    double* ans = malloc(sizeof(double) * 2);
    ans[0] = celsius + 273.15;
    ans[1] = celsius * 1.8 + 32;
    *returnSize = 2;
    return ans;
}
