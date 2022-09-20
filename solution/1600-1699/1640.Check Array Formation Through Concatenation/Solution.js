/**
 * @param {number[]} arr
 * @param {number[][]} pieces
 * @return {boolean}
 */
var canFormArray = function (arr, pieces) {
    const d = new Map();
    for (const p of pieces) {
        d.set(p[0], p);
    }
    for (let i = 0; i < arr.length; ) {
        if (!d.has(arr[i])) {
            return false;
        }
        const p = d.get(arr[i]);
        for (const v of p) {
            if (arr[i++] != v) {
                return false;
            }
        }
    }
    return true;
};
