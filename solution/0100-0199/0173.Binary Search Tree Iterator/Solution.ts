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
    private data: TreeNode[];

    constructor(root: TreeNode | null) {
        this.data = [];
        for (; root != null; root = root.left) {
            this.data.push(root);
        }
    }

    next(): number {
        const node = this.data.pop()!;
        for (let cur = node.right; cur != null; cur = cur.left) {
            this.data.push(cur);
        }
        return node.val;
    }

    hasNext(): boolean {
        return this.data.length > 0;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * var obj = new BSTIterator(root)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
