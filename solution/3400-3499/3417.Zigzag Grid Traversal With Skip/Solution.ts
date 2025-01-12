function zigzagTraversal(grid: number[][]): number[] {
    const ans: number[] = [];
    let ok: boolean = true;
    for (let i = 0; i < grid.length; ++i) {
        if (i % 2) {
            grid[i].reverse();
        }
        for (const x of grid[i]) {
            if (ok) {
                ans.push(x);
            }
            ok = !ok;
        }
    }
    return ans;
}
