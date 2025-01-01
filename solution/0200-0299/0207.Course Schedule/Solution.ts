function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    const g: number[][] = Array.from({ length: numCourses }, () => []);
    const indeg: number[] = Array(numCourses).fill(0);
    for (const [a, b] of prerequisites) {
        g[b].push(a);
        indeg[a]++;
    }
    const q: number[] = [];
    for (let i = 0; i < numCourses; ++i) {
        if (indeg[i] === 0) {
            q.push(i);
        }
    }
    for (const i of q) {
        --numCourses;
        for (const j of g[i]) {
            if (--indeg[j] === 0) {
                q.push(j);
            }
        }
    }
    return numCourses === 0;
}
