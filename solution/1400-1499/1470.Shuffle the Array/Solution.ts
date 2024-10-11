function shuffle(nums: number[], n: number): number[] {
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        ans.push(nums[i], nums[i + n]);
    }
    return ans;
}
