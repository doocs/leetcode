function firstMissingPositive(nums: number[]): number {
    const set = new Set(nums);
    let ans = 1;
    while (true) {
        if (!set.has(ans)) return ans;
        ans++;
    }
}
