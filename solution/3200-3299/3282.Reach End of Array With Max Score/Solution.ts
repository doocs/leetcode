function findMaximumScore(nums: number[]): number {
    let [ans, mx]: [number, number] = [0, 0];
    for (const x of nums.slice(0, -1)) {
        mx = Math.max(mx, x);
        ans += mx;
    }
    return ans;
}
