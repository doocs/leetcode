function highestPeak(isWater: number[][]): number[][] {
    const m = isWater.length,
        n = isWater[0].length;
    let ans: Array<Array<number>> = Array.from({ length: m }, v =>
        new Array(n).fill(-1),
    );
    // BFS
    let queue: Array<Array<number>> = []; // i, j, num
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (isWater[i][j]) {
                ans[i][j] = 0;
                queue.push([i, j, 0]);
            }
        }
    }
    const directions = [
        [0, -1],
        [-1, 0],
        [0, 1],
        [1, 0],
    ]; // left, up, right, down
    while (queue.length) {
        // 消除push/shift出现超时问题
        let tmp: Array<Array<number>> = [];
        for (const [i, j, num] of queue) {
            for (const [dx, dy] of directions) {
                const x = i + dx,
                    y = j + dy;
                // 校验合法的相邻格子
                if (x > -1 && x < m && y > -1 && y < n && ans[x][y] == -1) {
                    ans[x][y] = num + 1;
                    tmp.push([x, y, num + 1]);
                }
            }
        }
        queue = tmp;
    }
    return ans;
}
