function countCoveredBuildings(n: number, buildings: number[][]): number {
    const g1: Map<number, number[]> = new Map();
    const g2: Map<number, number[]> = new Map();

    for (const [x, y] of buildings) {
        if (!g1.has(x)) g1.set(x, []);
        g1.get(x)?.push(y);

        if (!g2.has(y)) g2.set(y, []);
        g2.get(y)?.push(x);
    }

    for (const list of g1.values()) {
        list.sort((a, b) => a - b);
    }
    for (const list of g2.values()) {
        list.sort((a, b) => a - b);
    }

    let ans = 0;

    for (const [x, y] of buildings) {
        const l1 = g1.get(x)!;
        const l2 = g2.get(y)!;

        if (l2[0] < x && x < l2[l2.length - 1] && l1[0] < y && y < l1[l1.length - 1]) {
            ans++;
        }
    }

    return ans;
}
