/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func tree2str(root *TreeNode) string {
	var b strings.Builder
	type frame struct {
		node  *TreeNode
		state int
	}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		node, state := cur.node, cur.state
		if state == 0 {
			if node == nil {
				continue
			}
			b.WriteString(strconv.Itoa(node.Val))
			if node.Left == nil && node.Right == nil {
				continue
			}
			b.WriteByte('(')
			stk = append(stk, frame{node, 1}, frame{node.Left, 0})
			continue
		}
		if state == 1 {
			b.WriteByte(')')
			if node.Right != nil {
				b.WriteByte('(')
				stk = append(stk, frame{node, 2}, frame{node.Right, 0})
			}
			continue
		}
		b.WriteByte(')')
	}
	return b.String()
}
