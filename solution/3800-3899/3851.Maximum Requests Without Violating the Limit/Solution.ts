function maxRequests(requests: number[][], k: number, window: number): number {
    const g = new Map<number, number[]>();
    for (const [u, t] of requests) {
        if (!g.has(u)) g.set(u, []);
        g.get(u)!.push(t);
    }
    let ans = requests.length;
    for (const ts of g.values()) {
        ts.sort((a, b) => a - b);
        const kept: number[] = [];
        let head = 0;
        for (const t of ts) {
            while (head < kept.length && t - kept[head] > window) {
                head++;
            }
            if (kept.length - head < k) {
                kept.push(t);
            } else {
                --ans;
            }
        }
    }
    return ans;
}
