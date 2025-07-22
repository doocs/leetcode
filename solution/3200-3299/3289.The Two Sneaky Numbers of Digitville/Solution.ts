function getSneakyNumbers(nums: number[]): number[] {
    const ans: number[] = [];
    const cnt: number[] = Array(100).fill(0);
    for (const x of nums) {
        if (++cnt[x] > 1) {
            ans.push(x);
        }
    }
    return ans;
}
