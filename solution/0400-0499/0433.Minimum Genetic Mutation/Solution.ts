function minMutation(startGene: string, endGene: string, bank: string[]): number {
    const q: [string, number][] = [[startGene, 0]];
    const vis = new Set<string>([startGene]);
    for (const [gene, depth] of q) {
        if (gene === endGene) {
            return depth;
        }
        for (const next of bank) {
            let c = 2;
            for (let k = 0; k < 8 && c > 0; ++k) {
                if (gene[k] !== next[k]) {
                    --c;
                }
            }
            if (c && !vis.has(next)) {
                q.push([next, depth + 1]);
                vis.add(next);
            }
        }
    }
    return -1;
}
