/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func equalToDescendants(root *TreeNode) (ans int) {
	sub := map[*TreeNode]int{}
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{}
	if root != nil {
		stk = append(stk, frame{root, 0})
	}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node := cur.node
		if cur.state == 0 {
			stk = append(stk, frame{node, 1})
			if node.Right != nil {
				stk = append(stk, frame{node.Right, 0})
			}
			if node.Left != nil {
				stk = append(stk, frame{node.Left, 0})
			}
			continue
		}
		l, r := 0, 0
		if node.Left != nil {
			l = sub[node.Left]
		}
		if node.Right != nil {
			r = sub[node.Right]
		}
		if l+r == node.Val {
			ans++
		}
		sub[node] = node.Val + l + r
	}
	return
}
