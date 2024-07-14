function allPathsSourceTarget(graph: number[][]): number[][] {
    const ans: number[][] = [];

    const dfs = (path: number[]) => {
        const curr = path.at(-1)!;
        if (curr === graph.length - 1) {
            ans.push([...path]);
            return;
        }

        for (const v of graph[curr]) {
            path.push(v);
            dfs(path);
            path.pop();
        }
    };

    dfs([0]);

    return ans;
}
