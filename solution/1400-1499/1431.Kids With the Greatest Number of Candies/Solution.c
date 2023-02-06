#define max(a, b) (((a) > (b)) ? (a) : (b))
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
bool* kidsWithCandies(int* candies, int candiesSize, int extraCandies, int* returnSize) {
    int mx = 0;
    for (int i = 0; i < candiesSize; i++) {
        mx = max(mx, candies[i]);
    }
    bool* ans = malloc(candiesSize * sizeof(bool));
    for (int i = 0; i < candiesSize; i++) {
        ans[i] = candies[i] + extraCandies >= mx;
    }
    *returnSize = candiesSize;
    return ans;
}
