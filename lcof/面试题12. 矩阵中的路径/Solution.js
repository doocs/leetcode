/**
 * @param {character[][]} board
 * @param {string} word
 * @return {boolean}
 */
var exist = function (board, word) {
    let row = board.length;
    let col = board[0].length;
    let res = false;
    let isRead = [...new Array(row)].map(() => Array(col).fill(0));
    for (let i = 0; i < row; i++) {
        for (let j = 0; j < col; j++) {
            if (res) break;
            if (board[i][j] === word[0]) {
                dfs(i, j, word);
            }
        }
    }
    function dfs(i, j, word) {
        if (
            i < 0 ||
            j < 0 ||
            i >= row ||
            j >= col ||
            res ||
            isRead[i][j] ||
            board[i][j] !== word[0]
        ) {
            return;
        }
        isRead[i][j] = 1;
        word = word.substring(1);
        if (word.length) {
            dfs(i - 1, j, word);
            dfs(i + 1, j, word);
            dfs(i, j - 1, word);
            dfs(i, j + 1, word);
        } else {
            res = true;
            return;
        }
        isRead[i][j] = 0;
    }
    return res;
};
