class Hashing {
    private p: bigint[];
    private h: bigint[];
    private mod: bigint;

    constructor(word: string, base: number, mod: number) {
        const n = word.length;
        this.mod = BigInt(mod);
        const b = BigInt(base);
        this.p = new Array(n + 1);
        this.h = new Array(n + 1);
        this.p[0] = 1n;
        this.h[0] = 0n;
        for (let i = 1; i <= n; i++) {
            this.p[i] = (this.p[i - 1] * b) % this.mod;
            this.h[i] = (this.h[i - 1] * b + BigInt(word.charCodeAt(i - 1))) % this.mod;
        }
    }

    public query(l: number, r: number): number {
        const res =
            (this.h[r] - ((this.h[l - 1] * this.p[r - l + 1]) % this.mod) + this.mod) % this.mod;
        return Number(res);
    }
}

function minimumCost(target: string, words: string[], costs: number[]): number {
    const base = 13331;
    const mod = 998244353;
    const inf = 1e9;
    const n = target.length;
    const hashing = new Hashing(target, base, mod);
    const f = new Array(n + 1).fill(inf);
    f[0] = 0;

    const ss = Array.from(new Set(words.map(w => w.length))).sort((a, b) => a - b);
    const d = new Map<number, number>();

    for (let i = 0; i < words.length; i++) {
        let x = 0n;
        const b = BigInt(base);
        const m = BigInt(mod);
        const word = words[i];
        for (let j = 0; j < word.length; j++) {
            x = (x * b + BigInt(word.charCodeAt(j))) % m;
        }
        const hashVal = Number(x);
        d.set(hashVal, Math.min(d.get(hashVal) ?? inf, costs[i]));
    }

    for (let i = 1; i <= n; i++) {
        for (const j of ss) {
            if (j > i) break;
            const x = hashing.query(i - j + 1, i);
            if (d.has(x)) {
                f[i] = Math.min(f[i], f[i - j] + d.get(x)!);
            }
        }
    }

    return f[n] >= inf ? -1 : f[n];
}
