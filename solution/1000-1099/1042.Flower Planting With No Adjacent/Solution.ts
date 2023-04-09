function gardenNoAdj(n: number, paths: number[][]): number[] {
    const g: number[][] = new Array(n).fill(0).map(() => []);
    for (const [x, y] of paths) {
        g[x - 1].push(y - 1);
        g[y - 1].push(x - 1);
    }
    const ans: number[] = new Array(n).fill(0);
    for (let x = 0; x < n; ++x) {
        const used: boolean[] = new Array(5).fill(false);
        for (const y of g[x]) {
            used[ans[y]] = true;
        }
        for (let c = 1; c < 5; ++c) {
            if (!used[c]) {
                ans[x] = c;
                break;
            }
        }
    }
    return ans;
}
