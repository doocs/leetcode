function findErrorNums(nums: number[]): number[] {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    const ans: number[] = new Array(2).fill(0);
    for (let x = 1; x <= n; ++x) {
        const t = cnt.get(x) || 0;
        if (t === 2) {
            ans[0] = x;
        } else if (t === 0) {
            ans[1] = x;
        }
    }
    return ans;
}
