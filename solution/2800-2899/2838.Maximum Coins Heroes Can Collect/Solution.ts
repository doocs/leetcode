function maximumCoins(heroes: number[], monsters: number[], coins: number[]): number[] {
    const m = monsters.length;
    const idx: number[] = Array.from({ length: m }, (_, i) => i);
    idx.sort((i, j) => monsters[i] - monsters[j]);
    const s: number[] = Array(m + 1).fill(0);
    for (let i = 0; i < m; ++i) {
        s[i + 1] = s[i] + coins[idx[i]];
    }
    const search = (x: number): number => {
        let l = 0;
        let r = m;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (monsters[idx[mid]] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    return heroes.map(h => s[search(h)]);
}
