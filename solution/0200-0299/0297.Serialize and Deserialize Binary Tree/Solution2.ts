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

/*
 * Encodes a tree to a single string.
 */
function serialize(root: TreeNode | null): string {
    if (root == null) {
        return '#';
    }
    const { val, left, right } = root;
    return `${val},${serialize(left)},${serialize(right)}`;
}

/*
 * Decodes your encoded data to tree.
 */
function deserialize(data: string): TreeNode | null {
    const n = data.length;
    if (n === 1) {
        return null;
    }
    const vals = data.split(',').reverse();
    const renew = () => {
        const val = vals.pop();
        if (val == null || val === '#') {
            return null;
        }
        return new TreeNode(Number(val), renew(), renew());
    };
    return renew();
}

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */
