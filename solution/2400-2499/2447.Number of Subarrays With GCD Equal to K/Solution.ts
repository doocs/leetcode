function subarrayGCD(nums: number[], k: number): number {
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let g = 0;
        for (let j = i; j < n; ++j) {
            g = gcd(g, nums[j]);
            if (g === k) {
                ++ans;
            }
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
