function maxWalls(robots: number[], distance: number[], walls: number[]): number {
    type Pair = [number, number];
    const n = robots.length;
    const arr: Pair[] = robots.map((r, i) => [r, distance[i]]);

    _.sortBy(arr, p => p[0]).forEach((p, i) => (arr[i] = p));
    walls.sort((a, b) => a - b);
    const f: number[][] = Array.from({ length: n }, () => Array(2).fill(0));

    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < 2; ++j) {
            let left = arr[i][0] - arr[i][1];
            if (i > 0) {
                left = Math.max(left, arr[i - 1][0] + 1);
            }
            let l = _.sortedIndex(walls, left);
            let r = _.sortedIndex(walls, arr[i][0] + 1);
            let ans = (i > 0 ? f[i - 1][0] : 0) + (r - l);

            let right = arr[i][0] + arr[i][1];
            if (i + 1 < n) {
                if (j === 0) {
                    right = Math.min(right, arr[i + 1][0] - arr[i + 1][1] - 1);
                } else {
                    right = Math.min(right, arr[i + 1][0] - 1);
                }
            }
            l = _.sortedIndex(walls, arr[i][0]);
            r = _.sortedIndex(walls, right + 1);
            ans = Math.max(ans, (i > 0 ? f[i - 1][1] : 0) + (r - l));
            f[i][j] = ans;
        }
    }
    return f[n - 1][1];
}
