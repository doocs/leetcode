const mx = 100010;
const prime = Array(mx).fill(true);
prime[0] = prime[1] = false;
for (let i = 2; i <= mx; ++i) {
    if (prime[i]) {
        for (let j = i + i; j <= mx; j += i) {
            prime[j] = false;
        }
    }
}

class UnionFind {
    p: number[];
    size: number[];
    constructor(n: number) {
        this.p = Array(n)
            .fill(0)
            .map((_, i) => i);
        this.size = Array(n).fill(1);
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): boolean {
        const [pa, pb] = [this.find(a), this.find(b)];
        if (pa === pb) {
            return false;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
        return true;
    }

    getSize(x: number): number {
        return this.size[this.find(x)];
    }
}

function countPaths(n: number, edges: number[][]): number {
    const uf = new UnionFind(n + 1);
    const g: number[][] = Array(n + 1)
        .fill(0)
        .map(() => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
        if (!prime[u] && !prime[v]) {
            uf.union(u, v);
        }
    }
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        if (prime[i]) {
            let t = 0;
            for (let j of g[i]) {
                if (!prime[j]) {
                    const cnt = uf.getSize(j);
                    ans += cnt + t * cnt;
                    t += cnt;
                }
            }
        }
    }
    return ans;
}
