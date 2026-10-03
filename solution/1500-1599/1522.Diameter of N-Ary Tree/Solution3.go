/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func diameter(root *Node) int {
	if root == nil {
		return 0
	}
	type frame struct {
		node  *Node
		state int
	}
	ans := 0
	height := map[*Node]int{}
	stk := []frame{{root, 0}}
	for len(stk) > 0 {
		f := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if f.state == 0 {
			stk = append(stk, frame{f.node, 1})
			children := f.node.Children
			for i := len(children) - 1; i >= 0; i-- {
				if children[i] != nil {
					stk = append(stk, frame{children[i], 0})
				}
			}
		} else {
			m1, m2 := 0, 0
			for _, child := range f.node.Children {
				t := height[child]
				if t > m1 {
					m2, m1 = m1, t
				} else if t > m2 {
					m2 = t
				}
			}
			ans = max(ans, m1+m2)
			height[f.node] = m1 + 1
		}
	}
	return ans
}
