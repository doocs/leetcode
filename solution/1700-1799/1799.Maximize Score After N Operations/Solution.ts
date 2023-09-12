function maxScore(nums: number[]): number {
    const m = nums.length;
    const f: number[] = new Array(1 << m).fill(0);
    const g: number[][] = new Array(m).fill(0).map(() => new Array(m).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = i + 1; j < m; ++j) {
            g[i][j] = gcd(nums[i], nums[j]);
        }
    }
    for (let k = 0; k < 1 << m; ++k) {
        const cnt = bitCount(k);
        if (cnt % 2 === 0) {
            for (let i = 0; i < m; ++i) {
                if ((k >> i) & 1) {
                    for (let j = i + 1; j < m; ++j) {
                        if ((k >> j) & 1) {
                            const t = f[k ^ (1 << i) ^ (1 << j)] + ~~(cnt / 2) * g[i][j];
                            f[k] = Math.max(f[k], t);
                        }
                    }
                }
            }
        }
    }
    return f[(1 << m) - 1];
}

function gcd(a: number, b: number): number {
    return b ? gcd(b, a % b) : a;
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
