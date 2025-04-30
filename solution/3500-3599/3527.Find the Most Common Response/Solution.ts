function findCommonResponse(responses: string[][]): string {
    const cnt = new Map<string, number>();
    for (const ws of responses) {
        const s = new Set<string>();
        for (const w of ws) {
            if (!s.has(w)) {
                s.add(w);
                cnt.set(w, (cnt.get(w) ?? 0) + 1);
            }
        }
    }
    let ans = responses[0][0];
    for (const [w, v] of cnt) {
        const best = cnt.get(ans)!;
        if (best < v || (best === v && w < ans)) {
            ans = w;
        }
    }
    return ans;
}
