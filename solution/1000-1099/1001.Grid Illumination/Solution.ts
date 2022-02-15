function gridIllumination(
    n: number,
    lamps: number[][],
    queries: number[][],
): number[] {
    let lights: Set<string> = new Set();
    let rows: Map<number, number> = new Map(); // i
    let cols: Map<number, number> = new Map(); // j
    let mainDiagonal: Map<number, number> = new Map(); // i - j
    let subDiagonal: Map<number, number> = new Map(); // i + j
    for (let [i, j] of lamps) {
        let key = `${i},${j}`;
        if (lights.has(key)) continue;
        lights.add(key);
        rows.set(i, (rows.get(i) || 0) + 1);
        cols.set(j, (cols.get(j) || 0) + 1);
        mainDiagonal.set(i - j, (mainDiagonal.get(i - j) || 0) + 1);
        subDiagonal.set(i + j, (subDiagonal.get(i + j) || 0) + 1);
    }

    let ans: Array<number> = [];
    let directions = [
        [-1, -1],
        [-1, 0],
        [-1, 1],
        [0, -1],
        [0, 0],
        [0, 1],
        [1, -1],
        [1, 0],
        [1, 1],
    ];
    for (let [i, j] of queries) {
        // check
        const check =
            lights.has(`${i},${j}`) ||
            rows.get(i) ||
            cols.get(j) ||
            mainDiagonal.get(i - j) ||
            subDiagonal.get(i + j);
        ans.push(check ? 1 : 0);
        // close lamp
        for (let [dx, dy] of directions) {
            const [x, y] = [i + dx, j + dy];
            let key = `${x},${y}`;
            if (x < 0 || x > n - 1 || y < 0 || y > n - 1 || !lights.has(key)) {
                continue;
            }
            lights.delete(key);
            rows.set(x, rows.get(x) - 1);
            cols.set(y, cols.get(y) - 1);
            mainDiagonal.set(x - y, mainDiagonal.get(x - y) - 1);
            subDiagonal.set(x + y, subDiagonal.get(x + y) - 1);
        }
    }
    return ans;
}
