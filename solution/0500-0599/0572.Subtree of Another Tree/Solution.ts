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
function isSubtree(root: TreeNode | null, subRoot: TreeNode | null): boolean {
    const same = (p: TreeNode | null, q: TreeNode | null): boolean => {
        if (!p || !q) {
            return p === q;
        }
        return p.val === q.val && same(p.left, q.left) && same(p.right, q.right);
    };
    if (!root) {
        return false;
    }
    return same(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
}
