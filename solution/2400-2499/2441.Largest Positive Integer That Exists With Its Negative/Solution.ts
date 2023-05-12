function findMaxK(nums: number[]): number {
    let ans = -1;
    const s = new Set(nums);
    for (const x of s) {
        if (s.has(-x)) {
            ans = Math.max(ans, x);
        }
    }
    return ans;
}
