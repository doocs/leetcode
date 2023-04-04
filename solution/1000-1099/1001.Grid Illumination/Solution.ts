function gridIllumination(
    n: number,
    lamps: number[][],
    queries: number[][],
): number[] {
    const row = new Map<number, number>();
    const col = new Map<number, number>();
    const diag1 = new Map<number, number>();
    const diag2 = new Map<number, number>();
    const s = new Set<number>();
    for (const [i, j] of lamps) {
        if (s.has(i * n + j)) {
            continue;
        }
        s.add(i * n + j);
        row.set(i, (row.get(i) || 0) + 1);
        col.set(j, (col.get(j) || 0) + 1);
        diag1.set(i - j, (diag1.get(i - j) || 0) + 1);
        diag2.set(i + j, (diag2.get(i + j) || 0) + 1);
    }
    const ans: number[] = [];
    for (const [i, j] of queries) {
        if (
            row.get(i)! > 0 ||
            col.get(j)! > 0 ||
            diag1.get(i - j)! > 0 ||
            diag2.get(i + j)! > 0
        ) {
            ans.push(1);
        } else {
            ans.push(0);
        }
        for (let x = i - 1; x <= i + 1; ++x) {
            for (let y = j - 1; y <= j + 1; ++y) {
                if (x < 0 || x >= n || y < 0 || y >= n || !s.has(x * n + y)) {
                    continue;
                }
                s.delete(x * n + y);
                row.set(x, row.get(x)! - 1);
                col.set(y, col.get(y)! - 1);
                diag1.set(x - y, diag1.get(x - y)! - 1);
                diag2.set(x + y, diag2.get(x + y)! - 1);
            }
        }
    }
    return ans;
}
