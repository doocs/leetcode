/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pseudoPalindromicPaths(root *TreeNode) (ans int) {
	stk := []struct {
		node *TreeNode
		mask int
	}{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, mask := cur.node, cur.mask
		if node == nil {
			continue
		}
		mask ^= 1 << node.Val
		if node.Left == nil && node.Right == nil {
			if mask&(mask-1) == 0 {
				ans++
			}
		} else {
			stk = append(stk, struct {
				node *TreeNode
				mask int
			}{node.Right, mask}, struct {
				node *TreeNode
				mask int
			}{node.Left, mask})
		}
	}
	return
}
