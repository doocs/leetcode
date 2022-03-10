/**
 * // Definition for a Node.
 * function Node(val, left, right) {
 *      this.val = val;
 *      this.left = left;
 *      this.right = right;
 *  };
 */

/**
 * @param {Node} root
 * @return {Node}
 */
var treeToDoublyList = function (root) {
    if (!root) return root;
    let prev = null;
    let head = null;

    function dfs(root) {
        if (!root) return;
        dfs(root.left);
        if (prev) {
            prev.right = root;
            root.left = prev;
        } else {
            head = root;
        }
        prev = root;
        dfs(root.right);
    }
    dfs(root);
    prev.right = head;
    head.left = prev;
    return head;
};
