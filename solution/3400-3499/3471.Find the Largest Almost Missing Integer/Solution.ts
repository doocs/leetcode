function largestInteger(nums: number[], k: number): number {
    if (k === 1) {
        const cnt = new Map<number, number>();
        for (const x of nums) {
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
        let ans = -1;
        for (const [x, v] of cnt.entries()) {
            if (v === 1 && x > ans) {
                ans = x;
            }
        }
        return ans;
    }

    const n = nums.length;
    if (k === n) {
        return Math.max(...nums);
    }

    const f = (k: number): number => {
        for (let i = 0; i < n; i++) {
            if (i !== k && nums[i] === nums[k]) {
                return -1;
            }
        }
        return nums[k];
    };

    return Math.max(f(0), f(n - 1));
}
