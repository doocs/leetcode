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

class BSTIterator {
    private stack: TreeNode[];

    constructor(root: TreeNode | null) {
        this.stack = [];
        const dfs = (root: TreeNode | null) => {
            if (root == null) {
                return;
            }
            this.stack.push(root);
            dfs(root.left);
        };
        dfs(root);
    }

    next(): number {
        const { val, right } = this.stack.pop();
        if (right) {
            let cur = right;
            while (cur != null) {
                this.stack.push(cur);
                cur = cur.left;
            }
        }
        return val;
    }

    hasNext(): boolean {
        return this.stack.length !== 0;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
