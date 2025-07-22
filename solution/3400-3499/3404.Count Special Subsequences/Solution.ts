function numberOfSubsequences(nums: number[]): number {
    const n = nums.length;
    const cnt = new Map<number, number>();

    function gcd(a: number, b: number): number {
        while (b !== 0) {
            [a, b] = [b, a % b];
        }
        return a;
    }

    for (let r = 4; r < n - 2; r++) {
        const c = nums[r];
        for (let s = r + 2; s < n; s++) {
            const d = nums[s];
            const g = gcd(c, d);
            const key = ((d / g) << 12) | (c / g);
            cnt.set(key, (cnt.get(key) || 0) + 1);
        }
    }

    let ans = 0;
    for (let q = 2; q < n - 4; q++) {
        const b = nums[q];
        for (let p = 0; p < q - 1; p++) {
            const a = nums[p];
            const g = gcd(a, b);
            const key = ((a / g) << 12) | (b / g);
            ans += cnt.get(key) || 0;
        }
        const c = nums[q + 2];
        for (let s = q + 4; s < n; s++) {
            const d = nums[s];
            const g = gcd(c, d);
            const key = ((d / g) << 12) | (c / g);
            cnt.set(key, (cnt.get(key) || 0) - 1);
        }
    }

    return ans;
}
