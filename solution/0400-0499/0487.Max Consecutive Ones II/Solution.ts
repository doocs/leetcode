function findMaxConsecutiveOnes(nums: number[]): number {
    let [l, cnt] = [0, 0];
    for (const x of nums) {
        cnt += x ^ 1;
        if (cnt > 1) {
            cnt -= nums[l++] ^ 1;
        }
    }
    return nums.length - l;
}
