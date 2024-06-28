function findLHS(nums: number[]): number {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    let ans = 0;
    for (const [x, c] of Object.entries(cnt)) {
        const y = +x + 1;
        if (cnt[y]) {
            ans = Math.max(ans, c + cnt[y]);
        }
    }
    return ans;
}
