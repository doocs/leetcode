/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
var serialize = function (root) {
    let data = [];
    let serializeRec = function (root) {
        if (!root) {
            data.push(1001);
            return null;
        }
        data.push(root.val);

        serializeRec(root.left);
        serializeRec(root.right);
    }
    serializeRec(root);
    return data;
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function (data) {
    if (!data) {
        return null;
    }
    let curVal = data.shift();
    if (curVal == 1001) {
        return null;
    }
    let node = new TreeNode(curVal);
    node.left = deserialize(data);
    node.right = deserialize(data);
    return node;
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */