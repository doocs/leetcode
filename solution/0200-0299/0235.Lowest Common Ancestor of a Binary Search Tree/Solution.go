/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val   int
 *     Left  *TreeNode
 *     Right *TreeNode
 * }
 */

func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	for {
		if root.Val < min(p.Val, q.Val) {
			root = root.Right
		} else if root.Val > max(p.Val, q.Val) {
			root = root.Left
		} else {
			return root
		}
	}
}
