int minimumOperations(int* nums, int numsSize) {
    int vis[101] = {0};
    vis[0] = 1;
    int ans = 0;
    for (int i = 0; i < numsSize; i++) {
        if (vis[nums[i]]) {
            continue;
        }
        vis[nums[i]] = 1;
        ans++;
    }
    return ans;
}
