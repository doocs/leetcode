/**
 * @param {number[]} arr
 * @param {number} m
 * @return {number}
 */
const findLatestStep = function (arr, m) {
    let p = [];
    let size = [];

    function find(x) {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    function union(a, b) {
        const pa = find(a);
        const pb = find(b);
        if (pa === pb) {
            return;
        }
        p[pa] = pb;
        size[pb] += size[pa];
    }

    const n = arr.length;
    if (m === n) {
        return n;
    }
    const vis = new Array(n).fill(false);
    p = new Array(n);
    size = new Array(n).fill(1);
    for (let i = 0; i < n; ++i) {
        p[i] = i;
    }
    let ans = -1;
    for (let i = 0; i < n; ++i) {
        const v = arr[i] - 1;
        if (v > 0 && vis[v - 1]) {
            if (size[find(v - 1)] === m) {
                ans = i;
            }
            union(v, v - 1);
        }
        if (v < n - 1 && vis[v + 1]) {
            if (size[find(v + 1)] === m) {
                ans = i;
            }
            union(v, v + 1);
        }
        vis[v] = true;
    }
    return ans;
};
