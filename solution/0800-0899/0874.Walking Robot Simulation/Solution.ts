function robotSim(commands: number[], obstacles: number[][]): number {
    const dirs = [0, 1, 0, -1, 0];
    const s: Set<number> = new Set();
    const f = (x: number, y: number) => x * 60010 + y;
    for (const [x, y] of obstacles) {
        s.add(f(x, y));
    }
    let [ans, x, y, k] = [0, 0, 0, 0];
    for (let c of commands) {
        if (c === -2) {
            k = (k + 3) % 4;
        } else if (c === -1) {
            k = (k + 1) % 4;
        } else {
            while (c-- > 0) {
                const [nx, ny] = [x + dirs[k], y + dirs[k + 1]];
                if (s.has(f(nx, ny))) {
                    break;
                }
                [x, y] = [nx, ny];
                ans = Math.max(ans, x * x + y * y);
            }
        }
    }
    return ans;
}
