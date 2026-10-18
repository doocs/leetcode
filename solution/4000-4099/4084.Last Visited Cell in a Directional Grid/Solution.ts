function lastVisitedCell(grid: string[][]): number[] {
    const m = grid.length,
        n = grid[0].length;
    let r = 0,
        c = 0;
    const vis = Array.from({ length: m }, () => Array(n).fill(false));
    while (true) {
        const cur = [r, c];
        vis[r][c] = true;
        switch (grid[r][c]) {
            case 'L':
                c--;
                break;
            case 'R':
                c++;
                break;
            case 'U':
                r--;
                break;
            case 'D':
                r++;
                break;
        }
        if (r >= 0 && r < m && c >= 0 && c < n && !vis[r][c]) {
            continue;
        }
        return cur;
    }
}
