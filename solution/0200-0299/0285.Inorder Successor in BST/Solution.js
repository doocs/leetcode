/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */
/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @return {TreeNode}
 */
var inorderSuccessor = function (root, p) {
	function findMin(root) {
		if (!root) {
			return null;
		}
		while (root.left) {
			root = root.left;
		}
		return root;
	}
	if (!root) {
		return null;
	}
	let successor = null;
	while (root) {
		if (root.val > p.val) {
			successor = root;
			root = root.left;
		} else if (root.val < p.val) {
			root = root.right;
		} else {
			if (root.right) {
				successor = findMin(root.right);
			}
			break;
		}
	}
	return successor;
};