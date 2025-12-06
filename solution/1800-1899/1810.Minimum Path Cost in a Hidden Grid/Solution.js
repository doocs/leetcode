/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * function GridMaster() {
 *
 *     @param {character} direction
 *     @return {boolean}
 *     this.canMove = function(direction) {
 *         ...
 *     };
 *     @param {character} direction
 *     @return {integer}
 *     this.move = function(direction) {
 *         ...
 *     };
 *     @return {boolean}
 *     this.isTarget = function() {
 *         ...
 *     };
 * };
 */

/**
 * @param {GridMaster} master
 * @return {integer}
 */
var findShortestPath = function (master) {
    const [m, n] = [200, 200];
    const [sx, sy] = [100, 100];
    const inf = Number.MAX_SAFE_INTEGER;
    const dirs = [-1, 0, 1, 0, -1];
    const s = ['U', 'R', 'D', 'L'];
    const g = Array.from({ length: m }, () => Array(n).fill(-1));
    let target = [-1, -1];
    const dfs = (x, y) => {
        if (master.isTarget()) {
            target = [x, y];
        }
        for (let k = 0; k < 4; ++k) {
            const dx = dirs[k],
                dy = dirs[k + 1];
            const nx = x + dx,
                ny = y + dy;
            if (
                0 <= nx &&
                nx < m &&
                0 <= ny &&
                ny < n &&
                g[nx][ny] === -1 &&
                master.canMove(s[k])
            ) {
                g[nx][ny] = master.move(s[k]);
                dfs(nx, ny);
                master.move(s[(k + 2) % 4]);
            }
        }
    };
    g[sx][sy] = 0;
    dfs(sx, sy);
    if (target[0] === -1 && target[1] === -1) {
        return -1;
    }
    const dist = Array.from({ length: m }, () => Array(n).fill(inf));
    dist[sx][sy] = 0;
    const pq = new MinPriorityQueue(node => node[0]);
    pq.enqueue([0, sx, sy]);
    while (!pq.isEmpty()) {
        const [w, x, y] = pq.dequeue();
        if (x === target[0] && y === target[1]) {
            return w;
        }
        if (w > dist[x][y]) {
            continue;
        }
        for (let k = 0; k < 4; ++k) {
            const nx = x + dirs[k],
                ny = y + dirs[k + 1];
            if (0 <= nx && nx < m && 0 <= ny && ny < n && g[nx][ny] !== -1) {
                const nd = w + g[nx][ny];
                if (nd < dist[nx][ny]) {
                    dist[nx][ny] = nd;
                    pq.enqueue([nd, nx, ny]);
                }
            }
        }
    }
    return -1;
};
