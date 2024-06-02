function nearestExit(maze: string[][], entrance: number[]): number {
    const m = maze.length;
    const n = maze[0].length;
    const dir = [0, 1, 0, -1, 0];
    const q = [[...entrance, 0]];
    maze[entrance[0]][entrance[1]] = '+';
    for (const [i, j, ans] of q) {
        for (let d = 0; d < 4; d++) {
            const [x, y] = [i + dir[d], j + dir[d + 1]];
            const v = maze[x]?.[y];
            if (!v && ans) {
                return ans;
            }
            if (v === '.') {
                q.push([x, y, ans + 1]);
                maze[x][y] = '+';
            }
        }
    }
    return -1;
}
