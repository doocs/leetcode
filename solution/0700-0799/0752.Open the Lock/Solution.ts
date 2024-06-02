function openLock(deadends: string[], target: string): number {
    const vis = Array<boolean>(10_000);
    const q = [[0, 0, 0, 0, 0]];
    const t = +target;
    deadends.forEach(s => (vis[+s] = true));

    for (const [a, b, c, d, ans] of q) {
        const key = a * 1000 + b * 100 + c * 10 + d;
        if (vis[key]) {
            continue;
        }

        vis[key] = true;
        if (key === t) {
            return ans;
        }

        for (let i = 0; i < 4; i++) {
            const next = [a, b, c, d, ans + 1];
            const prev = [a, b, c, d, ans + 1];
            next[i] = (next[i] + 11) % 10;
            prev[i] = (prev[i] + 9) % 10;
            q.push(prev, next);
        }
    }

    return -1;
}
