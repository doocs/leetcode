function partitionArray(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let prev = nums[0] + k;
    for (let num of nums) {
        if (num <= prev) continue;
        prev = num + k;
        ans++;
    }
    return ans;
}
