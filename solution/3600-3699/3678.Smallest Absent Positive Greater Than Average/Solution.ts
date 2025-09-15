function smallestAbsent(nums: number[]): number {
    const s = new Set<number>(nums);
    const sum = nums.reduce((a, b) => a + b, 0);
    let ans = Math.max(1, Math.floor(sum / nums.length) + 1);
    while (s.has(ans)) {
        ans++;
    }
    return ans;
}
