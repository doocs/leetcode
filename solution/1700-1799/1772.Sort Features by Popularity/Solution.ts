function sortFeatures(features: string[], responses: string[]): string[] {
    const cnt: Map<string, number> = new Map();
    for (const s of responses) {
        const vis: Set<string> = new Set();
        for (const w of s.split(' ')) {
            if (vis.has(w)) {
                continue;
            }
            vis.add(w);
            cnt.set(w, (cnt.get(w) || 0) + 1);
        }
    }
    const n = features.length;
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => {
        const x = cnt.get(features[i]) || 0;
        const y = cnt.get(features[j]) || 0;
        return x === y ? i - j : y - x;
    });
    return idx.map(i => features[i]);
}
