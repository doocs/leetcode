function sumDivisibleByK(nums: number[], k: number): number {
    const cnt = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = 0;
    for (const [x, v] of cnt.entries()) {
        if (v % k === 0) {
            ans += x * v;
        }
    }
    return ans;
}
