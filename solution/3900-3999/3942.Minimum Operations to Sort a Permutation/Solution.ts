function minOperations(nums: number[]): number {
    const n = nums.length;

    const zero = nums.indexOf(0);

    const check = (step: number): boolean => {
        for (let i = 1; i < n; i++) {
            const prev = (zero + (i - 1) * step + n) % n;
            const curr = (zero + i * step + n) % n;

            if (nums[prev] > nums[curr]) {
                return false;
            }
        }

        return true;
    };

    let ans = Number.MAX_SAFE_INTEGER;

    if (check(1)) {
        ans = Math.min(ans, zero);
        ans = Math.min(ans, n - zero + 2);
    }

    if (check(-1)) {
        ans = Math.min(ans, zero + 2);
        ans = Math.min(ans, n - zero);
    }

    return ans === Number.MAX_SAFE_INTEGER ? -1 : ans;
}
