function rotateTheBox(boxGrid: string[][]): string[][] {
    const m = boxGrid.length;
    const n = boxGrid[0].length;
    const ans: string[][] = Array.from({ length: n }, () => Array(m));

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans[j][m - i - 1] = boxGrid[i][j];
        }
    }

    for (let j = 0; j < m; j++) {
        const q: number[] = [];
        for (let i = n - 1; i >= 0; i--) {
            if (ans[i][j] === '*') {
                q.length = 0;
            } else if (ans[i][j] === '.') {
                q.push(i);
            } else if (q.length > 0) {
                const t = q.shift()!;
                ans[t][j] = '#';
                ans[i][j] = '.';
                q.push(i);
            }
        }
    }

    return ans;
}
