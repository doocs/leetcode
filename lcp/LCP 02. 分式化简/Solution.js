/**
 * @param {number[]} cont
 * @return {number[]}
 */
var fraction = function (cont) {
    function dfs(i) {
        if (i == cont.length - 1) {
            return [cont[i], 1];
        }
        const [a, b] = dfs(i + 1);
        return [a * cont[i] + b, a];
    }
    return dfs(0);
};
