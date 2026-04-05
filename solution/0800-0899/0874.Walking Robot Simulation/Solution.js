/**
 * @param {number[]} commands
 * @param {number[][]} obstacles
 * @return {number}
 */
var robotSim = function (commands, obstacles) {
    const dirs = [0, 1, 0, -1, 0];
    const s = new Set();

    const f = (x, y) => x * 60010 + y;

    for (const [x, y] of obstacles) {
        s.add(f(x, y));
    }

    let x = 0,
        y = 0,
        k = 0;
    let ans = 0;

    for (let c of commands) {
        if (c === -2) {
            k = (k + 3) % 4;
        } else if (c === -1) {
            k = (k + 1) % 4;
        } else {
            while (c-- > 0) {
                const nx = x + dirs[k];
                const ny = y + dirs[k + 1];
                if (s.has(f(nx, ny))) {
                    break;
                }
                x = nx;
                y = ny;
                ans = Math.max(ans, x * x + y * y);
            }
        }
    }

    return ans;
};
