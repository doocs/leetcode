function maxPairStrength(nums: number[]): number {
    const n = nums.length;
    let ans = 0;

    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            const g = gcd(nums[i], nums[j]);
            const x = Math.floor((nums[i] * nums[j]) / (g * g));
            ans = Math.max(ans, x);
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    while (b !== 0) {
        const t = a % b;
        a = b;
        b = t;
    }
    return a;
}
