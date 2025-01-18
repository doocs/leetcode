function leastBricks(wall: number[][]): number {
    const cnt: Map<number, number> = new Map();
    for (const row of wall) {
        let s = 0;
        for (let i = 0; i + 1 < row.length; ++i) {
            s += row[i];
            cnt.set(s, (cnt.get(s) || 0) + 1);
        }
    }
    const mx = Math.max(...cnt.values(), 0);
    return wall.length - mx;
}
