function divisibleTripletCount(nums: number[], d: number): number {
    const n = nums.length;
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let j = 0; j < n; ++j) {
        for (let k = j + 1; k < n; ++k) {
            const x = (d - ((nums[j] + nums[k]) % d)) % d;
            ans += cnt.get(x) || 0;
        }
        cnt.set(nums[j] % d, (cnt.get(nums[j] % d) || 0) + 1);
    }
    return ans;
}
