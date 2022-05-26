/**
 * @param {number[]} arr
 * @param {number[][]} pieces
 * @return {boolean}
 */
var canFormArray = function (arr, pieces) {
    let mapper = new Map();
    for (let i = 0; i < pieces.length; i++) {
        mapper.set(pieces[i][0], pieces[i]);
    }
    let i = 0,
        n = arr.length;
    while (i < n) {
        let cur = arr[i];
        let nums = mapper.get(cur);
        if (nums == undefined) return false;
        for (let num of nums) {
            if (arr[i] != num) return false;
            i++;
        }
    }
    return true;
};
