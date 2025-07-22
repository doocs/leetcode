class Pair {
    constructor(
        public key: string,
        public value: number,
    ) {}
}

function maxAmount(
    initialCurrency: string,
    pairs1: string[][],
    rates1: number[],
    pairs2: string[][],
    rates2: number[],
): number {
    const d1 = build(pairs1, rates1, initialCurrency);
    const d2 = build(pairs2, rates2, initialCurrency);
    let ans = 0;
    for (const [currency, rate] of Object.entries(d2)) {
        if (currency in d1) {
            ans = Math.max(ans, d1[currency] / rate);
        }
    }
    return ans;
}

function build(pairs: string[][], rates: number[], init: string): { [key: string]: number } {
    const g: { [key: string]: Pair[] } = {};
    const d: { [key: string]: number } = {};
    for (let i = 0; i < pairs.length; ++i) {
        const a = pairs[i][0];
        const b = pairs[i][1];
        const r = rates[i];
        if (!g[a]) g[a] = [];
        if (!g[b]) g[b] = [];
        g[a].push(new Pair(b, r));
        g[b].push(new Pair(a, 1 / r));
    }
    dfs(g, d, init, 1.0);
    return d;
}

function dfs(
    g: { [key: string]: Pair[] },
    d: { [key: string]: number },
    a: string,
    v: number,
): void {
    if (a in d) {
        return;
    }

    d[a] = v;
    for (const pair of g[a] || []) {
        const b = pair.key;
        const r = pair.value;
        if (!(b in d)) {
            dfs(g, d, b, v * r);
        }
    }
}
