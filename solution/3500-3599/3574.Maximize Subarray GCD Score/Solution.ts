function maxGCDScore(nums: number[], k: number): number {
    const n = nums.length;
    const cnt: number[] = Array(n).fill(0);

    for (let i = 0; i < n; ++i) {
        let x = nums[i];
        while (x % 2 === 0) {
            cnt[i]++;
            x /= 2;
        }
    }

    let ans = 0;
    for (let l = 0; l < n; ++l) {
        let g = 0;
        let mi = Number.MAX_SAFE_INTEGER;
        let t = 0;
        for (let r = l; r < n; ++r) {
            g = gcd(g, nums[r]);
            if (cnt[r] < mi) {
                mi = cnt[r];
                t = 1;
            } else if (cnt[r] === mi) {
                t++;
            }
            const len = r - l + 1;
            const score = (t > k ? g : g * 2) * len;
            ans = Math.max(ans, score);
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    while (b !== 0) {
        const temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}
