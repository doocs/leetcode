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
    readonly #s = new Set<number>();

    constructor(root: TreeNode | null) {
        root.val = 0;

        const dfs = (node: TreeNode | null, x = 0) => {
            if (!node) return;

            this.#s.add(x);
            dfs(node.left, x * 2 + 1);
            dfs(node.right, x * 2 + 2);
        };

        dfs(root);
    }

    find(target: number): boolean {
        return this.#s.has(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * var obj = new FindElements(root)
 * var param_1 = obj.find(target)
 */
