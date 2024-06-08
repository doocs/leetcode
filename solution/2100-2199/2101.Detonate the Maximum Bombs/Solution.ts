function maximumDetonation(bombs: number[][]): number {
    const n = bombs.length;
    const g = new Map<number, number[]>(bombs.map((_, i) => [i, []]));

    for (let i = 0; i < n - 1; i++) {
        for (let j = 1; j < n; j++) {
            const [x1, y1, r1] = bombs[i];
            const [x2, y2, r2] = bombs[j];
            const distance = Math.hypot(x1 - x2, y1 - y2);

            if (distance <= r1) g.get(i)!.push(j);
            if (distance <= r2) g.get(j)!.push(i);
        }
    }

    let res = 0;
    for (let i = 0; i < n; i++) {
        const seen = new Set<number>([i]);
        const q = [i];

        for (const i of q) {
            for (const j of g.get(i) ?? []) {
                if (seen.has(j)) continue;
                seen.add(j);
                q.push(j);
            }
        }

        if (seen.size === n) return n;
        res = Math.max(res, seen.size);
    }

    return res;
}
