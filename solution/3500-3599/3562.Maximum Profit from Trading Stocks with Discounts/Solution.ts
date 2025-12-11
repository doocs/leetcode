function maxProfit(
    n: number,
    present: number[],
    future: number[],
    hierarchy: number[][],
    budget: number,
): number {
    const g: number[][] = Array.from({ length: n + 1 }, () => []);

    for (const [u, v] of hierarchy) {
        g[u].push(v);
    }

    const dfs = (u: number): number[][] => {
        const nxt: number[][] = Array.from({ length: budget + 1 }, () => [0, 0]);

        for (const v of g[u]) {
            const fv = dfs(v);
            for (let j = budget; j >= 0; j--) {
                for (let jv = 0; jv <= j; jv++) {
                    for (let pre = 0; pre < 2; pre++) {
                        nxt[j][pre] = Math.max(nxt[j][pre], nxt[j - jv][pre] + fv[jv][pre]);
                    }
                }
            }
        }

        const f: number[][] = Array.from({ length: budget + 1 }, () => [0, 0]);
        const price = future[u - 1];

        for (let j = 0; j <= budget; j++) {
            for (let pre = 0; pre < 2; pre++) {
                const cost = Math.floor(present[u - 1] / (pre + 1));
                if (j >= cost) {
                    const profitIfBuy = nxt[j - cost][1] + (price - cost);
                    f[j][pre] = Math.max(nxt[j][0], profitIfBuy);
                } else {
                    f[j][pre] = nxt[j][0];
                }
            }
        }

        return f;
    };

    return dfs(1)[budget][0];
}
