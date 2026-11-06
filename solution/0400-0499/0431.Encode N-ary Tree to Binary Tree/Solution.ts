/**
 * Definition for _Node.
 * class _Node {
 *     val: number
 *     children: _Node[]
 *
 *     constructor(v: number) {
 *         this.val = v;
 *         this.children = [];
 *     }
 * }
 */

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

class Codec {
    constructor() {}

    // Encodes an n-ary tree to a binary tree.
    encode(root: _Node | null): TreeNode | null {
        if (root === null) {
            return null;
        }
        const node = new TreeNode(root.val);
        if (root.children.length === 0) {
            return node;
        }
        let left: TreeNode | null = this.encode(root.children[0]);
        node.left = left;
        for (let i = 1; i < root.children.length; i++) {
            if (left) {
                left.right = this.encode(root.children[i]);
                left = left.right;
            }
        }
        return node;
    }

    // Decodes your binary tree back to an n-ary tree.
    decode(root: TreeNode | null): _Node | null {
        if (root === null) {
            return null;
        }
        const node = new _Node(root.val);
        if (root.left === null) {
            return node;
        }
        let left: TreeNode | null = root.left;
        while (left !== null) {
            node.children.push(this.decode(left));
            left = left.right;
        }
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
