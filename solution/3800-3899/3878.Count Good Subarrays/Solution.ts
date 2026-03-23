function countGoodSubarrays(nums: number[]): number {
    const n = nums.length;

    const l = new Array(n).fill(-1);
    const stk: number[] = [];

    for (let i = 0; i < n; i++) {
        const x = nums[i];
        while (
            stk.length &&
            nums[stk[stk.length - 1]] < x &&
            (nums[stk[stk.length - 1]] | x) === x
        ) {
            stk.pop();
        }
        l[i] = stk.length ? stk[stk.length - 1] : -1;
        stk.push(i);
    }

    const r = new Array(n).fill(n);
    stk.length = 0;

    for (let i = n - 1; i >= 0; i--) {
        while (stk.length && (nums[stk[stk.length - 1]] | nums[i]) === nums[i]) {
            stk.pop();
        }
        r[i] = stk.length ? stk[stk.length - 1] : n;
        stk.push(i);
    }

    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans += (i - l[i]) * (r[i] - i);
    }
    return ans;
}
