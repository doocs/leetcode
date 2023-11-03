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
    private nums: number[];
    private n: number;
    private i: number;

    constructor(root: TreeNode | null) {
        this.nums = [];
        const dfs = (root: TreeNode | null) => {
            if (!root) {
                return;
            }
            dfs(root.left);
            this.nums.push(root.val);
            dfs(root.right);
        };
        dfs(root);
        this.n = this.nums.length;
        this.i = -1;
    }

    hasNext(): boolean {
        return this.i < this.n - 1;
    }

    next(): number {
        return this.nums[++this.i];
    }

    hasPrev(): boolean {
        return this.i > 0;
    }

    prev(): number {
        return this.nums[--this.i];
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.hasNext()
 * var param_2 = obj.next()
 * var param_3 = obj.hasPrev()
 * var param_4 = obj.prev()
 */
