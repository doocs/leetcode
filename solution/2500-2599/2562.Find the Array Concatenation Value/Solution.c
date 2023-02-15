int getLen(int num) {
    int res = 0;
    while (num) {
        num /= 10;
        res++;
    }
    return res;
}

long long findTheArrayConcVal(int* nums, int numsSize) {
    long long ans = 0;
    int i = 0;
    int j = numsSize - 1;
    while (i < j) {
        ans += nums[i] * pow(10, getLen(nums[j])) + nums[j];
        i++;
        j--;
    }
    if (i == j) {
        ans += nums[i];
    }
    return ans;
}
