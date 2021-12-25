/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func postorder(root *Node) []int {
	if root == nil {
		return []int{}
	}
	var stk []*Node
	var ans []int
	stk = append(stk, root)
	for len(stk) > 0 {
		node := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		ans = append([]int{node.Val}, ans...)
		for _, child := range node.Children {
			stk = append(stk, child)
		}
	}
	return ans
}