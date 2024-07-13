function minimumOperations(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        const mod = x % 3;
        if (mod) {
            ans += Math.min(mod, 3 - mod);
        }
    }
    return ans;
}
