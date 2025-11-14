class Hashing {
    private p: bigint[];
    private h: bigint[];
    private mod: bigint;

    constructor(s: string, base: bigint = 13331n, mod: bigint = 998244353n) {
        const n = s.length;
        this.mod = mod;
        this.p = new Array<bigint>(n + 1).fill(1n);
        this.h = new Array<bigint>(n + 1).fill(0n);
        for (let i = 1; i <= n; i++) {
            this.p[i] = (this.p[i - 1] * base) % mod;
            this.h[i] = (this.h[i - 1] * base + BigInt(s.charCodeAt(i - 1))) % mod;
        }
    }

    query(l: number, r: number): bigint {
        return (this.h[r] - ((this.h[l - 1] * this.p[r - l + 1]) % this.mod) + this.mod) % this.mod;
    }
}

function maxFreq(s: string, maxLetters: number, minSize: number, maxSize: number): number {
    const n = s.length;
    const hashing = new Hashing(s);
    const freq = new Array<number>(256).fill(0);
    let k = 0;
    let ans = 0;
    const cnt = new Map<bigint, number>();

    for (let i = 1; i <= n; i++) {
        const c = s.charCodeAt(i - 1);
        if (++freq[c] === 1) {
            k++;
        }

        if (i >= minSize) {
            if (k <= maxLetters) {
                const x = hashing.query(i - minSize + 1, i);
                const v = (cnt.get(x) || 0) + 1;
                cnt.set(x, v);
                ans = Math.max(ans, v);
            }
            const j = i - minSize;
            const c2 = s.charCodeAt(j);
            if (--freq[c2] === 0) {
                k--;
            }
        }
    }

    return ans;
}
