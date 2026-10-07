/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func goodNodes(root *TreeNode) (ans int) {
	stk := []struct {
		node *TreeNode
		mx   int
	}{{root, -10001}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, mx := cur.node, cur.mx
		if node == nil {
			continue
		}
		if mx <= node.Val {
			ans++
			mx = node.Val
		}
		stk = append(stk, struct {
			node *TreeNode
			mx   int
		}{node.Right, mx}, struct {
			node *TreeNode
			mx   int
		}{node.Left, mx})
	}
	return
}
