/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @param {number} startValue
 * @param {number} destValue
 * @return {string}
 */
var getDirections = function (root, startValue, destValue) {
    const dfs = (node, x, path) => {
        if (node === null) {
            return false;
        }
        if (node.val === x) {
            return true;
        }
        path.push('L');
        if (dfs(node.left, x, path)) {
            return true;
        }
        path[path.length - 1] = 'R';
        if (dfs(node.right, x, path)) {
            return true;
        }
        path.pop();
        return false;
    };
    const pathToStart = [];
    const pathToDest = [];
    dfs(root, startValue, pathToStart);
    dfs(root, destValue, pathToDest);
    let i = 0;
    while (pathToStart[i] === pathToDest[i]) {
        ++i;
    }
    return 'U'.repeat(pathToStart.length - i) + pathToDest.slice(i).join('');
};
