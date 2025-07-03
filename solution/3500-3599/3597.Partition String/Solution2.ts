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

function partitionString(s: string): string[] {
    const n = s.length;
    const hashing = new Hashing(s);
    const vis = new Set<string>();
    const ans: string[] = [];
    let l = 1;
    for (let r = 1; r <= n; r++) {
        const x = hashing.query(l, r).toString();
        if (!vis.has(x)) {
            vis.add(x);
            ans.push(s.slice(l - 1, r));
            l = r + 1;
        }
    }
    return ans;
}
