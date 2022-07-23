function findOrder(numCourses: number, prerequisites: number[][]): number[] {
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
    let ans = [];
    while (q.length) {
        const i = q.shift();
        ans.push(i);
        for (let j of g[i]) {
            if (--indeg[j] == 0) {
                q.push(j);
            }
        }
    }
    return ans.length == numCourses ? ans : [];
}
