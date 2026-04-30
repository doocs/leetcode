function minimumTotalDistance(robot: number[], factory: number[][]): number {
    robot.sort((a, b) => a - b);
    factory.sort((a, b) => a[0] - b[0]);

    const n = robot.length;
    const m = factory.length;
    const f: number[][] = Array.from({ length: n }, () => Array(m).fill(-1));

    const dfs = (i: number, j: number): number => {
        if (i === n) return 0;
        if (j === m) return 1e15;
        if (f[i][j] !== -1) return f[i][j];

        let ans = dfs(i, j + 1);
        let totalDist = 0;
        const [pos, capacity] = factory[j];

        for (let k = 0; k < capacity; k++) {
            if (i + k >= n) break;
            totalDist += Math.abs(robot[i + k] - pos);
            ans = Math.min(ans, totalDist + dfs(i + k + 1, j + 1));
        }

        return (f[i][j] = ans);
    };

    return dfs(0, 0);
}
