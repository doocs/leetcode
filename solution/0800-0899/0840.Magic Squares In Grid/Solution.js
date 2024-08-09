/**
 * @param {number[][]} grid
 * @return {number}
 */
var numMagicSquaresInside = function(grid) {
    const isMagic = (i, j) => {
        const s = new Set();
        for (let x = 0; x < 3; x++) {
            for (let y = 0; y < 3; y++) {
                const num = grid[i + x][j + y];
                if (num < 1 || num > 9 || s.has(num)) return false;
                s.add(num);
            }
        }

        const rowSum = grid[i][j] + grid[i][j+1] + grid[i][j+2];
        if (rowSum !== grid[i+1][j] + grid[i+1][j+1] + grid[i+1][j+2]) return false;
        if (rowSum !== grid[i+2][j] + grid[i+2][j+1] + grid[i+2][j+2]) return false;

        const colSum1 = grid[i][j] + grid[i+1][j] + grid[i+2][j];
        const colSum2 = grid[i][j+1] + grid[i+1][j+1] + grid[i+2][j+1];
        const colSum3 = grid[i][j+2] + grid[i+1][j+2] + grid[i+2][j+2];
        if (rowSum !== colSum1 || rowSum !== colSum2 || rowSum !== colSum3) return false;

        if (rowSum !== grid[i][j] + grid[i+1][j+1] + grid[i+2][j+2]) return false;
        if (rowSum !== grid[i][j+2] + grid[i+1][j+1] + grid[i+2][j]) return false;

        return true;
    };

    let count = 0;

    for (let i = 0; i < grid.length - 2; i++) {
        for (let j = 0; j < grid[0].length - 2; j++) {
            if (isMagic(i, j)) count++;
        }
    }

    return count;
};
