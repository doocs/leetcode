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

class FindElements {
    private s: Set<number> = new Set<number>();

    constructor(root: TreeNode | null) {
        root.val = 0;
        const dfs = (root: TreeNode) => {
            this.s.add(root.val);
            if (root.left) {
                root.left.val = root.val * 2 + 1;
                dfs(root.left);
            }
            if (root.right) {
                root.right.val = root.val * 2 + 2;
                dfs(root.right);
            }
        };
        dfs(root);
    }

    find(target: number): boolean {
        return this.s.has(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * var obj = new FindElements(root)
 * var param_1 = obj.find(target)
 */
