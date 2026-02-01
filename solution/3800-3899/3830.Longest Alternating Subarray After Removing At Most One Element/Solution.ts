function longestAlternating(nums: number[]): number {
    const n = nums.length;
    const l1 = new Array<number>(n).fill(1);
    const l2 = new Array<number>(n).fill(1);
    const r1 = new Array<number>(n).fill(1);
    const r2 = new Array<number>(n).fill(1);

    let ans = 0;

    for (let i = 1; i < n; i++) {
        if (nums[i - 1] < nums[i]) {
            l1[i] = l2[i - 1] + 1;
        } else if (nums[i - 1] > nums[i]) {
            l2[i] = l1[i - 1] + 1;
        }
        ans = Math.max(ans, l1[i]);
        ans = Math.max(ans, l2[i]);
    }

    for (let i = n - 2; i >= 0; i--) {
        if (nums[i + 1] > nums[i]) {
            r1[i] = r2[i + 1] + 1;
        } else if (nums[i + 1] < nums[i]) {
            r2[i] = r1[i + 1] + 1;
        }
    }

    for (let i = 1; i < n - 1; i++) {
        if (nums[i - 1] < nums[i + 1]) {
            ans = Math.max(ans, l2[i - 1] + r2[i + 1]);
        } else if (nums[i - 1] > nums[i + 1]) {
            ans = Math.max(ans, l1[i - 1] + r1[i + 1]);
        }
    }

    return ans;
}
