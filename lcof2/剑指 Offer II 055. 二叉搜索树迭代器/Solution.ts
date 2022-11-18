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
    private stack: number[];

    constructor(root: TreeNode | null) {
        this.stack = [];
        const dfs = ({ val, left, right }: TreeNode) => {
            right && dfs(right);
            this.stack.push(val);
            left && dfs(left);
        };
        dfs(root);
    }

    next(): number {
        return this.stack.pop();
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
