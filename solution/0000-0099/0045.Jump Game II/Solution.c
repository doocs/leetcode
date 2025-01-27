int jump(int* nums, int numsSize) {
    int ans = 0;
    int mx = 0;
    int last = 0;
    for (int i = 0; i < numsSize - 1; ++i) {
        mx = (mx > i + nums[i]) ? mx : (i + nums[i]);
        if (last == i) {
            ++ans;
            last = mx;
        }
    }
    return ans;
}
