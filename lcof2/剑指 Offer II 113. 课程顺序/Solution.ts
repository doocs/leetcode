function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    const g: number[][] = Array.from({ length: numCourses }, () => []);
    const indeg: number[] = Array(numCourses).fill(0);
    for (const [a, b] of prerequisites) {
        g[b].push(a);
        ++indeg[a];
    }
    const q: number[] = indeg.map((v, i) => (v === 0 ? i : -1)).filter(v => v !== -1);
    const ans: number[] = [];
    while (q.length) {
        const i = q.pop()!;
        ans.push(i);
        for (const j of g[i]) {
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return ans.length === numCourses ? ans : [];
}
