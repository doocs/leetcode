function shoppingOffers(price: number[], special: number[][], needs: number[]): number {
    const bits = 4;
    const n = needs.length;
    const f: Map<number, number> = new Map();

    let mask = 0;
    for (let i = 0; i < n; i++) {
        mask |= needs[i] << (i * bits);
    }

    const dfs = (cur: number): number => {
        if (f.has(cur)) {
            return f.get(cur)!;
        }
        let ans = 0;
        for (let i = 0; i < n; i++) {
            ans += price[i] * ((cur >> (i * bits)) & 0xf);
        }
        for (const offer of special) {
            let nxt = cur;
            let ok = true;
            for (let j = 0; j < n; j++) {
                if (((cur >> (j * bits)) & 0xf) < offer[j]) {
                    ok = false;
                    break;
                }
                nxt -= offer[j] << (j * bits);
            }
            if (ok) {
                ans = Math.min(ans, offer[n] + dfs(nxt));
            }
        }
        f.set(cur, ans);
        return ans;
    };

    return dfs(mask);
}
