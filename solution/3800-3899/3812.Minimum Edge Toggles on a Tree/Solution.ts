function minimumFlips(n: number, edges: number[][], start: string, target: string): number[] {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n - 1; i++) {
        const [a, b] = edges[i];
        g[a].push([b, i]);
        g[b].push([a, i]);
    }
    const ans: number[] = [];
    const dfs = (a: number, fa: number): boolean => {
        let rev = start[a] !== target[a];
        for (const [b, i] of g[a]) {
            if (b !== fa && dfs(b, a)) {
                ans.push(i);
                rev = !rev;
            }
        }
        return rev;
    };
    if (dfs(0, -1)) {
        return [-1];
    }
    ans.sort((x, y) => x - y);
    return ans;
}
