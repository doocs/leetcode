function gcdSum(nums: number[]): number {
    const n = nums.length;
    const prefixGcd: number[] = new Array(n);
    let mx = 0;

    for (let i = 0; i < n; i++) {
        const x = nums[i];
        mx = Math.max(mx, x);
        prefixGcd[i] = gcd(x, mx);
    }

    prefixGcd.sort((a, b) => a - b);

    let ans = 0;
    for (let i = 0; i < n >> 1; i++) {
        ans += gcd(prefixGcd[i], prefixGcd[n - i - 1]);
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
