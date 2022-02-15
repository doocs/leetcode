function canFinish(numCourses: number, prerequisites: number[][]): boolean {
    let edges: number[][] = Array.from({ length: numCourses }, () => []);
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

    let visited: number = 0;
    while (queue.length) {
        visited += 1;
        const u = queue.shift();
        for (let v of edges[u]) {
            indeg[v] -= 1;
            if (!indeg[v]) {
                queue.push(v);
            }
        }
    }
    return visited == numCourses;
}
