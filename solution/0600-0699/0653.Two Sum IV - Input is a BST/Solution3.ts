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

function findTarget(root: TreeNode | null, k: number): boolean {
    const vis = new Set<number>();
    const stk: (TreeNode | null)[] = [];
    if (root) {
        stk.push(root);
    }
    while (stk.length) {
        const node = stk.pop()!;
        if (!node) {
            continue;
        }
        if (vis.has(k - node.val)) {
            return true;
        }
        vis.add(node.val);
        if (node.right) {
            stk.push(node.right);
        }
        if (node.left) {
            stk.push(node.left);
        }
    }
    return false;
}
