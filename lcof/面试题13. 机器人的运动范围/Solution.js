/**
 * @param {number} m
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var movingCount = function (m, n, k) {
    let res = 0;
    let isRead = [...new Array(m)].map(() => Array(n).fill(0));
    let moving = [
        [0, -1],
        [0, 1],
        [1, 0],
        [-1, 0],
    ];
    let queue = [[0, 0]];
    isRead[0][0] = 1;
    while (queue.length) {
        let [x, y] = queue.shift();
        for (let [dx, dy] of moving) {
            let X = x + dx;
            let Y = y + dy;
            if (
                X >= 0 &&
                Y >= 0 &&
                X < m &&
                Y < n &&
                !isRead[X][Y] &&
                isValid(X, Y)
            ) {
                queue.push([X, Y]);
                isRead[X][Y] = 1;
            }
        }
        res++;
    }
    function isValid(x, y) {
        let r = 0;
        r +=
            x
                .toString()
                .split("")
                .reduce((acc, cur) => acc + +cur, 0) +
            y
                .toString()
                .split("")
                .reduce((acc, cur) => acc + +cur, 0);
        if (r <= k) return true;
        else return false;
    }
    return res;
};
