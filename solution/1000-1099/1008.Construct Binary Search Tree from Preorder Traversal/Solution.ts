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

function bstFromPreorder(preorder: number[]): TreeNode | null {
    const n = preorder.length;
    const next = new Array(n);
    const stack = [];
    for (let i = n - 1; i >= 0; i--) {
        while (
            stack.length !== 0 &&
            preorder[stack[stack.length - 1]] < preorder[i]
        ) {
            stack.pop();
        }
        next[i] = stack[stack.length - 1] ?? n;
        stack.push(i);
    }

    const dfs = (left: number, right: number) => {
        if (left >= right) {
            return null;
        }
        return new TreeNode(
            preorder[left],
            dfs(left + 1, next[left]),
            dfs(next[left], right),
        );
    };
    return dfs(0, n);
}
