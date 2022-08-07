function reachableNodes(
    n: number,
    edges: number[][],
    restricted: number[],
): number {
    let res = 0;
    const vis = new Array(n).fill(false);
    const map = new Map<number, number[]>();
    for (const [start, end] of edges) {
        map.set(start, [...(map.get(start) ?? []), end]);
        map.set(end, [...(map.get(end) ?? []), start]);
    }
    const dfs = (cur: number) => {
        if (restricted.includes(cur) || vis[cur]) {
            return;
        }
        res++;
        vis[cur] = true;
        for (const item of map.get(cur) ?? []) {
            dfs(item);
        }
    };
    dfs(0);

    return res;
}
