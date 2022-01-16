/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func preorder(root *Node) []int {
	var ans []int
	if root == nil {
		return ans
	}
	stk := []*Node{root}
	for len(stk) > 0 {
		node := stk[len(stk)-1]
		ans = append(ans, node.Val)
		stk = stk[:len(stk)-1]
		children := node.Children
		for i := len(children) - 1; i >= 0; i-- {
			stk = append(stk, children[i])
		}
	}
	return ans
}