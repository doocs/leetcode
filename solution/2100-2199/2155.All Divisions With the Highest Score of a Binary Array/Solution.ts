function maxScoreIndices(nums: number[]): number[] {
    const n = nums.length;
    let [l0, r1] = [0, nums.reduce((a, b) => a + b, 0)];
    let mx = r1;
    const ans: number[] = [0];
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        l0 += x ^ 1;
        r1 -= x;
        const t = l0 + r1;
        if (mx === t) {
            ans.push(i);
        } else if (mx < t) {
            mx = t;
            ans.length = 0;
            ans.push(i);
        }
    }
    return ans;
}
