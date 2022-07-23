function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    let g = Array.from({ length: numCourses }, () => []);
    let indeg = new Array(numCourses).fill(0);
    for (let [a, b] of prerequisites) {
        g[b].push(a);
        ++indeg[a];
    }
    let q = [];
    for (let i = 0; i < numCourses; ++i) {
        if (!indeg[i]) {
            q.push(i);
        }
    }
    let cnt = 0;
    while (q.length) {
        const i = q.shift();
        ++cnt;
        for (let j of g[i]) {
            if (--indeg[j] == 0) {
                q.push(j);
            }
        }
    }
    return cnt == numCourses;
}
