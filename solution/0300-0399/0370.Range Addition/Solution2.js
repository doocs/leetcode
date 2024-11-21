/**
 * @param {number} length
 * @param {number[][]} updates
 * @return {number[]}
 */
var getModifiedArray = function (length, updates) {
    class BinaryIndexedTree {
        constructor(n) {
            this.n = n;
            this.c = Array(n + 1).fill(0);
        }

        update(x, delta) {
            while (x <= this.n) {
                this.c[x] += delta;
                x += x & -x;
            }
        }

        query(x) {
            let s = 0;
            while (x > 0) {
                s += this.c[x];
                x -= x & -x;
            }
            return s;
        }
    }

    const bit = new BinaryIndexedTree(length);
    for (const [l, r, c] of updates) {
        bit.update(l + 1, c);
        bit.update(r + 2, -c);
    }
    return Array.from({ length }, (_, i) => bit.query(i + 1));
};
