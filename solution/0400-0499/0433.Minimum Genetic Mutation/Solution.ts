function minMutation(startGene: string, endGene: string, bank: string[]): number {
    const vis = new Set<string>();
    const q: [string, number][] = [[startGene, 0]];

    for (const [gene, depth] of q) {
        if (gene === endGene) return depth;
        if (vis.has(gene)) continue;
        vis.add(gene);

        for (const next of bank) {
            let c = 2;
            for (let k = 0; k < 8; k++) {
                if (gene[k] !== next[k]) c--;
                if (!c) break;
            }

            if (c) q.push([next, depth + 1]);
        }
    }

    return -1;
}
