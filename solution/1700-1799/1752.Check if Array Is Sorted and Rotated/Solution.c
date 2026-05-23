bool check(int* nums, int numsSize) {
    int cnt = 0;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] > nums[(i + 1) % numsSize]) {
            cnt++;
        }
    }
    return cnt <= 1;
}