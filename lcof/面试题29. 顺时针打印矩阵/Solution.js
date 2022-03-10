/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    if (!matrix || !matrix.length) return [];
    let row = matrix.length;
    let col = matrix[0].length;
    let res = [];
    let moves = {
        right: [0, 1],
        down: [1, 0],
        left: [0, -1],
        up: [-1, 0],
    };
    let k = 0;
    function dfs(i, j, dir) {
        if (
            i < 0 ||
            j < 0 ||
            i >= row ||
            j >= col ||
            res.length === row * col
        ) {
            return;
        }
        res.push(matrix[i][j]);
        switch (dir) {
            case 'right':
                if (j === col - 1 - k) dir = 'down';
                break;
            case 'down':
                if (i === row - 1 - k) dir = 'left';
                break;
            case 'left':
                if (j === k) {
                    dir = 'up';
                    k++;
                }
                break;
            case 'up':
                if (i === k) dir = 'right';
                break;
        }
        let x = i + moves[dir][0];
        let y = j + moves[dir][1];
        dfs(x, y, dir);
    }
    dfs(0, 0, 'right');
    return res;
};
