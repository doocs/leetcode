function generate(numRows: number): number[][] {
    const f: number[][] = [[1]];
    for (let i = 0; i < numRows - 1; ++i) {
        const g: number[] = [1];
        for (let j = 0; j < f[i].length - 1; ++j) {
            g.push(f[i][j] + f[i][j + 1]);
        }
        g.push(1);
        f.push(g);
    }
    return f;
}
