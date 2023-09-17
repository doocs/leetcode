function robot(command: string, obstacles: number[][], x: number, y: number): boolean {
    const f = (i: number, j: number): number => {
        return i * (10 ** 9 + 1) + j;
    };
    const vis: Set<number> = new Set();
    vis.add(f(0, 0));
    let [i, j] = [0, 0];
    for (const c of command) {
        if (c === 'U') {
            ++j;
        } else {
            ++i;
        }
        vis.add(f(i, j));
    }
    const k = Math.min(Math.floor(x / i), Math.floor(y / j));
    if (!vis.has(f(x - k * i, y - k * j))) {
        return false;
    }
    for (const [a, b] of obstacles) {
        if (a > x || b > y) {
            continue;
        }
        const k = Math.min(Math.floor(a / i), Math.floor(b / j));
        if (vis.has(f(a - k * i, b - k * j))) {
            return false;
        }
    }
    return true;
}
