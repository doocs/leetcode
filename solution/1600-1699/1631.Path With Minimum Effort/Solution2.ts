function minimumEffortPath(heights: number[][]): number {
    const check = (h: number): boolean => {
        const m = heights.length;
        const n = heights[0].length;
        const vis: boolean[][] = Array.from({ length: m }, () => Array(n).fill(false));
        const dirs: number[] = [-1, 0, 1, 0, -1];
        const q: [number, number][] = [[0, 0]];
        vis[0][0] = true;

        while (q.length > 0) {
            const [i, j] = q.pop()!;
            if (i === m - 1 && j === n - 1) {
                return true;
            }

            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (
                    x >= 0 &&
                    x < m &&
                    y >= 0 &&
                    y < n &&
                    !vis[x][y] &&
                    Math.abs(heights[x][y] - heights[i][j]) <= h
                ) {
                    q.push([x, y]);
                    vis[x][y] = true;
                }
            }
        }
        return false;
    };

    let [l, r] = [0, 10 ** 6];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
