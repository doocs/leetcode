/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val   int
 *     Left  *TreeNode
 *     Right *TreeNode
 * }
 */

func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	for root != nil {
		// 如果 p、q 的值都小于 root，说明 p、q 肯定在 root 的左子树中；
		// 如果 p、q 都大于 root，说明肯定在 root 的右子树中；
		// 如果一个在左一个在右，则说明此时的 root 记为对应的最近公共祖先。
		if root.Val > p.Val && root.Val > q.Val {
			root = root.Left
		} else if root.Val < p.Val && root.Val < q.Val {
			root = root.Right
		} else {
			return root
		}
	}
	return nil
}