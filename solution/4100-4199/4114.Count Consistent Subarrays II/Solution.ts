function countConsistent(nums: number[]): number {
    const n = nums.length;
    const left = new Array(n).fill(-1);
    const stk: number[] = [];
    for (let i = 0; i < n; i++) {
        const x = nums[i];
        while (stk.length && (nums[stk[stk.length - 1]] & x) === x) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }

    stk.length = 0;
    let ans = 0;
    for (let i = n - 1; i >= 0; i--) {
        const x = nums[i];
        while (
            stk.length &&
            nums[stk[stk.length - 1]] !== x &&
            (nums[stk[stk.length - 1]] & x) === x
        ) {
            stk.pop();
        }
        const right = stk.length ? stk[stk.length - 1] : n;
        stk.push(i);
        ans += (i - left[i]) * (right - i);
    }
    return ans;
}
