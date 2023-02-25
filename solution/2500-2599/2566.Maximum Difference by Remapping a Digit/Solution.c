int getLen(int num) {
    int res = 0;
    while (num) {
        num /= 10;
        res++;
    }
    return res;
}

int minMaxDifference(int num) {
    int n = getLen(num);
    int* nums = malloc(sizeof(int) * n);
    int t = num;
    for (int i = n - 1; i >= 0; i--) {
        nums[i] = t % 10;
        t /= 10;
    }
    int min = 0;
    for (int i = 0; i < n; i++) {
        min *= 10;
        if (nums[i] != nums[0]) {
            min += nums[i];
        }
    }
    int max = 0;
    int target = 10;
    for (int i = 0; i < n; i++) {
        max *= 10;
        if (target == 10 && nums[i] != 9) {
            target = nums[i];
        }
        max += nums[i] == target ? 9 : nums[i];
    }
    free(nums);
    return max - min;
}
