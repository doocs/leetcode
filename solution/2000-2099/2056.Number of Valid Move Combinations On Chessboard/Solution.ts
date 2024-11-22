const rookDirs: [number, number][] = [
    [1, 0],
    [-1, 0],
    [0, 1],
    [0, -1],
];
const bishopDirs: [number, number][] = [
    [1, 1],
    [1, -1],
    [-1, 1],
    [-1, -1],
];
const queenDirs = [...rookDirs, ...bishopDirs];

function countCombinations(pieces: string[], positions: number[][]): number {
    const n = pieces.length;
    const m = 9;
    let ans = 0;

    const dist = Array.from({ length: n }, () =>
        Array.from({ length: m }, () => Array(m).fill(-1)),
    );

    const end: [number, number, number][] = Array(n).fill([0, 0, 0]);

    const resetDist = (i: number) => {
        for (let j = 0; j < m; j++) {
            for (let k = 0; k < m; k++) {
                dist[i][j][k] = -1;
            }
        }
    };

    const checkStop = (i: number, x: number, y: number, t: number): boolean => {
        for (let j = 0; j < i; j++) {
            if (dist[j][x][y] >= t) {
                return false;
            }
        }
        return true;
    };

    const checkPass = (i: number, x: number, y: number, t: number): boolean => {
        for (let j = 0; j < i; j++) {
            if (dist[j][x][y] === t) {
                return false;
            }
            if (end[j][0] === x && end[j][1] === y && end[j][2] <= t) {
                return false;
            }
        }
        return true;
    };

    const isValid = (x: number, y: number): boolean => {
        return x >= 1 && x < m && y >= 1 && y < m;
    };

    const getDirs = (piece: string): [number, number][] => {
        switch (piece[0]) {
            case 'r':
                return rookDirs;
            case 'b':
                return bishopDirs;
            default:
                return queenDirs;
        }
    };

    const dfs = (i: number) => {
        if (i >= n) {
            ans++;
            return;
        }

        const [x, y] = positions[i];
        resetDist(i);
        dist[i][x][y] = 0;
        end[i] = [x, y, 0];

        if (checkStop(i, x, y, 0)) {
            dfs(i + 1);
        }

        const dirs = getDirs(pieces[i]);
        for (const [dx, dy] of dirs) {
            resetDist(i);
            dist[i][x][y] = 0;
            let nx = x + dx,
                ny = y + dy,
                nt = 1;

            while (isValid(nx, ny) && checkPass(i, nx, ny, nt)) {
                dist[i][nx][ny] = nt;
                end[i] = [nx, ny, nt];
                if (checkStop(i, nx, ny, nt)) {
                    dfs(i + 1);
                }
                nx += dx;
                ny += dy;
                nt++;
            }
        }
    };

    dfs(0);
    return ans;
}
