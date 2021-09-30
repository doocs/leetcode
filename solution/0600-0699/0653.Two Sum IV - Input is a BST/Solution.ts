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
    let set: Set<number> = new Set();
    return find(root, k, set);
};


function find(root: TreeNode | null, k: number, set: Set<number>): boolean {
    if (!root) return false;
    if (set.has(k - root.val)) return true;
    set.add(root.val);
    return find(root.left, k, set) || find(root.right, k, set);
}