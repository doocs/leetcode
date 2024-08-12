/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function getDirections(root: TreeNode | null, startValue: number, destValue: number): string {
    const lca = (node: TreeNode | null, p: number, q: number): TreeNode | null => {
        if (node === null || [p, q].includes(node.val)) {
            return node;
        }
        const left = lca(node.left, p, q);
        const right = lca(node.right, p, q);

        return left && right ? node : (left ?? right);
    };

    const dfs = (node: TreeNode | null, x: number, path: string[]): boolean => {
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

    const node = lca(root, startValue, destValue);
    const pathToStart: string[] = [];
    const pathToDest: string[] = [];
    dfs(node, startValue, pathToStart);
    dfs(node, destValue, pathToDest);
    return 'U'.repeat(pathToStart.length) + pathToDest.join('');
}
