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

function createBinaryTree(descriptions: number[][]): TreeNode | null {
    const map = new Map<number, [number, number]>();
    const isRoot = new Map<number, boolean>();
    for (const [parent, child, isLeft] of descriptions) {
        let [left, right] = map.get(parent) ?? [0, 0];
        if (isLeft) {
            left = child;
        } else {
            right = child;
        }
        if (!isRoot.has(parent)) {
            isRoot.set(parent, true);
        }
        isRoot.set(child, false);
        map.set(parent, [left, right]);
    }
    const dfs = (val: number) => {
        if (val === 0) {
            return null;
        }
        const [left, right] = map.get(val) ?? [0, 0];
        return new TreeNode(val, dfs(left), dfs(right));
    };
    for (const [key, val] of isRoot.entries()) {
        if (val) {
            return dfs(key);
        }
    }
    return null;
}
