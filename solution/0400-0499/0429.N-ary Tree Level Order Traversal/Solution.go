/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func levelOrder(root *Node) [][]int {
	var ans [][]int
	if root == nil {
		return ans
	}
	q := []*Node{root}
	for len(q) > 0 {
		var t []int
		for n := len(q); n > 0; n-- {
			root = q[0]
			q = q[1:]
			t = append(t, root.Val)
			for _, child := range root.Children {
				q = append(q, child)
			}
		}
		ans = append(ans, t)
	}
	return ans
}