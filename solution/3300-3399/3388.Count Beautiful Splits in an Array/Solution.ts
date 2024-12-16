function beautifulSplits(nums: number[]): number {
    const n = nums.length;
    const lcp: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));

    for (let i = n - 1; i >= 0; i--) {
        for (let j = n - 1; j > i; j--) {
            if (nums[i] === nums[j]) {
                lcp[i][j] = lcp[i + 1][j + 1] + 1;
            }
        }
    }

    let ans = 0;
    for (let i = 1; i < n - 1; i++) {
        for (let j = i + 1; j < n; j++) {
            const a = i <= j - i && lcp[0][i] >= i;
            const b = j - i <= n - j && lcp[i][j] >= j - i;
            if (a || b) {
                ans++;
            }
        }
    }

    return ans;
}
