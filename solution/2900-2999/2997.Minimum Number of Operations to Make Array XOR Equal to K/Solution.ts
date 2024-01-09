function minOperations(nums: number[], k: number): number {
    let ans = 0;
    for (let i = 0; i < 20; ++i) {
        let v = 0;
        for (const x of nums) {
            v ^= (x >> i) & 1;
        }
        ans += ((k >> i) & 1) ^ v;
    }
    return ans;
}
