function minValidStrings(words: string[], target: string): number {
    class Hashing {
        private p: bigint[];
        private h: bigint[];
        private mod: bigint;

        constructor(word: string, base: bigint, mod: bigint) {
            const n = word.length;
            this.p = new Array<bigint>(n + 1).fill(0n);
            this.h = new Array<bigint>(n + 1).fill(0n);
            this.mod = mod;
            this.p[0] = 1n;
            for (let i = 1; i <= n; ++i) {
                this.p[i] = (this.p[i - 1] * base) % mod;
                this.h[i] = (this.h[i - 1] * base + BigInt(word.charCodeAt(i - 1))) % mod;
            }
        }

        query(l: number, r: number): bigint {
            const res =
                (this.h[r] - ((this.h[l - 1] * this.p[r - l + 1]) % this.mod) + this.mod) %
                this.mod;
            return res;
        }
    }

    const base = 13331n;
    const mod = 998244353n;
    const hashing = new Hashing(target, base, mod);

    const m = Math.max(0, ...words.map(w => w.length));
    const s: Set<bigint>[] = Array.from({ length: m + 1 }, () => new Set<bigint>());

    for (const w of words) {
        let h = 0n;
        for (let j = 0; j < w.length; ++j) {
            h = (h * base + BigInt(w.charCodeAt(j))) % mod;
            s[j + 1].add(h);
        }
    }

    const n = target.length;
    let ans = 0;
    let last = 0;
    let mx = 0;

    const f = (i: number): number => {
        let l = 0;
        let r = Math.min(n - i, m);
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            const sub = hashing.query(i + 1, i + mid);
            if (s[mid].has(sub)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    };

    for (let i = 0; i < n; ++i) {
        const dist = f(i);
        mx = Math.max(mx, i + dist);
        if (i === last) {
            if (i === mx) {
                return -1;
            }
            last = mx;
            ans++;
        }
    }

    return ans;
}
