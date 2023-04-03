function findMatrix(nums: number[]): number[][] {
    const ans: number[][] = [];
    const n = nums.length;
    const cnt: number[] = new Array(n + 1).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    for (let x = 1; x <= n; ++x) {
        for (let j = 0; j < cnt[x]; ++j) {
            if (ans.length <= j) {
                ans.push([]);
            }
            ans[j].push(x);
        }
    }
    return ans;
}
