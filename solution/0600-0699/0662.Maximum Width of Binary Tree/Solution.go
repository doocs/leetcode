/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func widthOfBinaryTree(root *TreeNode) int {
	q := []pair{{root, 1}}
	ans := 0
	for len(q) > 0 {
		ans = max(ans, q[len(q)-1].i-q[0].i+1)
		for n := len(q); n > 0; n-- {
			p := q[0]
			q = q[1:]
			root = p.node
			if root.Left != nil {
				q = append(q, pair{root.Left, p.i << 1})
			}
			if root.Right != nil {
				q = append(q, pair{root.Right, p.i<<1 | 1})
			}
		}
	}
	return ans
}

type pair struct {
	node *TreeNode
	i    int
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}