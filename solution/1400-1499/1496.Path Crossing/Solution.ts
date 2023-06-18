function isPathCrossing(path: string): boolean {
    let [i, j] = [0, 0];
    const vis: Set<number> = new Set();
    vis.add(0);
    for (const c of path) {
        if (c === 'N') {
            --i;
        } else if (c === 'S') {
            ++i;
        } else if (c === 'E') {
            ++j;
        } else if (c === 'W') {
            --j;
        }
        const t = i * 20000 + j;
        if (vis.has(t)) {
            return true;
        }
        vis.add(t);
    }
    return false;
}
