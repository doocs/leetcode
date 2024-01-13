/**
 * @param {number[][]} graph
 * @return {number[]}
 */
var eventualSafeNodes = function (graph) {
    const n = graph.length;
    const rg = new Array(n).fill(0).map(() => new Array());
    const indeg = new Array(n).fill(0);
    const q = [];
    for (let i = 0; i < n; ++i) {
        for (let j of graph[i]) {
            rg[j].push(i);
        }
        indeg[i] = graph[i].length;
        if (indeg[i] == 0) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.shift();
        for (let j of rg[i]) {
            if (--indeg[j] == 0) {
                q.push(j);
            }
        }
    }
    let ans = [];
    for (let i = 0; i < n; ++i) {
        if (indeg[i] == 0) {
            ans.push(i);
        }
    }
    return ans;
};
