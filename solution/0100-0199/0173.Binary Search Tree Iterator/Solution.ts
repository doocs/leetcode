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
    private data: number[];
    private index: number;

    constructor(root: TreeNode | null) {
        this.index = 0;
        this.data = [];
        const dfs = (root: TreeNode | null) => {
            if (root == null) {
                return;
            }
            const { val, left, right } = root;
            dfs(left);
            this.data.push(val);
            dfs(right);
        };
        dfs(root);
    }

    next(): number {
        return this.data[this.index++];
    }

    hasNext(): boolean {
        return this.index < this.data.length;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
