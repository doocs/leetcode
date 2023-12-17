function maxFrequencyScore(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; i++) {
        s[i] = s[i - 1] + nums[i - 1];
    }

    let l: number = 0;
    let r: number = n;
    while (l < r) {
        const mid: number = (l + r + 1) >> 1;
        let ok: boolean = false;
        for (let i = 0; i <= n - mid; i++) {
            const j = i + mid;
            const x = nums[Math.floor((i + j) / 2)];
            const left = (Math.floor((i + j) / 2) - i) * x - (s[Math.floor((i + j) / 2)] - s[i]);
            const right = s[j] - s[Math.floor((i + j) / 2)] - (j - Math.floor((i + j) / 2)) * x;
            if (left + right <= k) {
                ok = true;
                break;
            }
        }
        if (ok) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
}
