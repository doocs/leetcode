function findOrder(numCourses: number, prerequisites: number[][]): number[] {
    let edges = Array.from({ length: numCourses }, ()  => ([]));
    let indeg = new Array(numCourses).fill(0);
    for (let [b, a] of prerequisites) {
        edges[a].push(b);
        indeg[b] += 1;
    }

    let queue = [];
    for (let i = 0; i < numCourses; i++) {
        if (!indeg[i]) {
            queue.push(i);
        }
    }

    let ans = [];
    while (queue.length) {
        const u = queue.shift();
        ans.push(u);
        for (let v of edges[u]) {
            indeg[v] -= 1;
            if (!indeg[v]) {
                queue.push(v);
            }
        }
    }
    return ans.length == numCourses ? ans : [];
};