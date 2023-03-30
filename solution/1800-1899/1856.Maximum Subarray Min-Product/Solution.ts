function maxSumMinProduct(nums: number[]): number {
    const n = nums.length;
    const left: number[] = new Array(n).fill(-1);
    const right: number[] = new Array(n).fill(n);
    let stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length && nums[stk[stk.length - 1]] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    stk = [];
    for (let i = n - 1; i >= 0; --i) {
        while (stk.length && nums[stk[stk.length - 1]] > nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans: bigint = 0n;
    const mod = 10 ** 9 + 7;
    for (let i = 0; i < n; ++i) {
        const t = BigInt(nums[i]) * BigInt(s[right[i]] - s[left[i] + 1]);
        if (ans < t) {
            ans = t;
        }
    }
    return Number(ans % BigInt(mod));
}
