function shuffle(nums: number[], n: number): number[] {
    let ans = [];
    for (let i = 0; i < n; i++) {
        ans.push(nums[i], nums[n + i]);
    }
    return ans;
}
