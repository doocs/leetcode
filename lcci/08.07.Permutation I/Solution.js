/**
 * @param {string} S
 * @return {string[]}
 */
var permutation = function (S) {
    let res = [];
    let arr = [...S];
    let prev = [];
    let record = new Array(S.length).fill(false);
    dfs(arr, 0, prev, record, res);
    return res;
};

function dfs(arr, depth, prev, record, res) {
    if (depth == arr.length) {
        res.push(prev.join(''));
        return;
    }
    for (let i = 0; i < arr.length; i++) {
        if (record[i]) {
            continue;
        }
        prev.push(arr[i]);
        record[i] = true;
        dfs(arr, depth + 1, prev, record, res);
        prev.pop();
        record[i] = false;
    }
}
