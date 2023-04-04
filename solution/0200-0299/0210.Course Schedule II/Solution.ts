function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    const g: number[][] = new Array(numCourses).fill(0).map(() => []);
    const indeg: number[] = new Array(numCourses).fill(0);
    for (const [a, b] of prerequisites) {
        g[b].push(a);
        indeg[a]++;
    }
    const q: number[] = [];
    for (let i = 0; i < numCourses; ++i) {
        if (indeg[i] == 0) {
            q.push(i);
        }
    }
    const ans: number[] = [];
    while (q.length) {
        const i = q.shift()!;
        ans.push(i);
        for (const j of g[i]) {
            if (--indeg[j] == 0) {
                q.push(j);
            }
        }
    }
    return ans.length === numCourses ? ans : [];
}
