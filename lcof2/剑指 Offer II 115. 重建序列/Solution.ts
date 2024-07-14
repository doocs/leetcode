function sequenceReconstruction(nums: number[], sequences: number[][]): boolean {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    const indeg: number[] = Array(n).fill(0);
    for (const seq of sequences) {
        for (let i = 1; i < seq.length; ++i) {
            const [a, b] = [seq[i - 1] - 1, seq[i] - 1];
            g[a].push(b);
            ++indeg[b];
        }
    }
    const q: number[] = indeg.map((v, i) => (v === 0 ? i : -1)).filter(v => v !== -1);
    while (q.length === 1) {
        const i = q.pop()!;
        for (const j of g[i]) {
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return q.length === 0;
}
