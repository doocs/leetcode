function minOperations(nums: number[]): number {
    const n = nums.length;
    let cnt = 0;
    for (const x of nums) {
        if (x === 1) {
            ++cnt;
        }
    }
    if (cnt > 0) {
        return n - cnt;
    }
    let mi = n + 1;
    for (let i = 0; i < n; ++i) {
        let g = 0;
        for (let j = i; j < n; ++j) {
            g = gcd(g, nums[j]);
            if (g === 1) {
                mi = Math.min(mi, j - i + 1);
            }
        }
    }
    return mi > n ? -1 : n - 1 + mi - 1;
}

function gcd(a: number, b: number): number {
    return b === 0 ? a : gcd(b, a % b);
}
