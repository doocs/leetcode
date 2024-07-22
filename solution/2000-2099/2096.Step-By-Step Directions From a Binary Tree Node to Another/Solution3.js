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
var getDirections = function (root, start, dest) {
    const dfs = (node, x, path = []) => {
        if (!node) return false;
        if (node.val === x) return true;

        path.push('L');
        if (dfs(node.left, x, path)) return true;

        path[path.length - 1] = 'R';
        if (dfs(node.right, x, path)) return true;
        path.pop();

        return false;
    };

    const startPath = [];
    const destPath = [];
    dfs(root, start, startPath);
    dfs(root, dest, destPath);

    let i = 0;
    while (startPath[i] === destPath[i]) i++;

    return (
        Array(startPath.length - i)
            .fill('U')
            .join('') + destPath.slice(i).join('')
    );
};
