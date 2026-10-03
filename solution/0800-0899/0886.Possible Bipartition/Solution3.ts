function possibleBipartition(n: number, dislikes: number[][]): boolean {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of dislikes) {
        g[a - 1].push(b - 1);
        g[b - 1].push(a - 1);
    }
    const color: number[] = new Array(n).fill(0);
    for (let start = 0; start < n; start++) {
        if (color[start] !== 0) {
            continue;
        }
        color[start] = 1;
        const stk: number[] = [start];
        while (stk.length) {
            const i = stk.pop()!;
            for (const j of g[i]) {
                if (color[j] === color[i]) {
                    return false;
                }
                if (color[j] === 0) {
                    color[j] = 3 - color[i];
                    stk.push(j);
                }
            }
        }
    }
    return true;
}
