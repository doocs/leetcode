function tupleSameProduct(nums: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (let i = 1; i < nums.length; ++i) {
        for (let j = 0; j < i; ++j) {
            const x = nums[i] * nums[j];
            cnt.set(x, (cnt.get(x) ?? 0) + 1);
        }
    }
    let ans = 0;
    for (const [_, v] of cnt) {
        ans += (v * (v - 1)) / 2;
    }
    return ans << 3;
}
