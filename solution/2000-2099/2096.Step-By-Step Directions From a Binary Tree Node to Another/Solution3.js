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
    const lca = (root, p, q) => {
        const ret = new Map();
        const stk = [[root, 0]];
        while (stk.length) {
            const [node, state] = stk.pop();
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === p || node.val === q) {
                    ret.set(node, node);
                    continue;
                }
                stk.push([node, 1]);
                stk.push([node.right, 0]);
                stk.push([node.left, 0]);
            } else {
                const left = node.left ? ret.get(node.left) : null;
                const right = node.right ? ret.get(node.right) : null;
                ret.set(node, left && right ? node : (left ?? right));
            }
        }
        return ret.get(root) ?? null;
    };

    const dfs = (start, x, path) => {
        const stk = [[start, 0]];
        while (stk.length) {
            const [node, state] = stk.pop();
            if (state === 0) {
                if (node === null) {
                    continue;
                }
                if (node.val === x) {
                    return true;
                }
                path.push('L');
                stk.push([node, 1]);
                stk.push([node.left, 0]);
            } else if (state === 1) {
                path[path.length - 1] = 'R';
                stk.push([node, 2]);
                stk.push([node.right, 0]);
            } else {
                path.pop();
            }
        }
        return false;
    };

    const node = lca(root, startValue, destValue);
    const pathToStart = [];
    const pathToDest = [];
    dfs(node, startValue, pathToStart);
    dfs(node, destValue, pathToDest);
    return 'U'.repeat(pathToStart.length) + pathToDest.join('');
};
