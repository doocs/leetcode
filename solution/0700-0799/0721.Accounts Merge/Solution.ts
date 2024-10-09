class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = new Array(n);
        this.size = new Array(n);
        for (let i = 0; i < n; ++i) {
            this.p[i] = i;
            this.size[i] = 1;
        }
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): boolean {
        let pa = this.find(a),
            pb = this.find(b);
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
}

function accountsMerge(accounts: string[][]): string[][] {
    const n = accounts.length;
    const uf = new UnionFind(n);
    const d = new Map<string, number>();

    for (let i = 0; i < n; ++i) {
        for (let j = 1; j < accounts[i].length; ++j) {
            const email = accounts[i][j];
            if (d.has(email)) {
                uf.union(i, d.get(email)!);
            } else {
                d.set(email, i);
            }
        }
    }

    const g = new Map<number, Set<string>>();
    for (let i = 0; i < n; ++i) {
        const root = uf.find(i);
        if (!g.has(root)) {
            g.set(root, new Set<string>());
        }
        const emailSet = g.get(root)!;
        for (let j = 1; j < accounts[i].length; ++j) {
            emailSet.add(accounts[i][j]);
        }
    }

    const ans: string[][] = [];
    for (const [root, emails] of g.entries()) {
        const emailList = Array.from(emails).sort();
        const mergedAccount = [accounts[root][0], ...emailList];
        ans.push(mergedAccount);
    }

    return ans;
}
