/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
type Node struct {
	node *TreeNode
	idx  int
}

func widthOfBinaryTree(root *TreeNode) int {
	q := []Node{{root, 1}}
	ans := 0
	for len(q) > 0 {
		ans = max(ans, q[len(q)-1].idx-q[0].idx+1)
		n := len(q)
		for i := 0; i < n; i++ {
			node := q[0]
			q = q[1:]
			if node.node.Left != nil {
				q = append(q, Node{node.node.Left, node.idx * 2})
			}
			if node.node.Right != nil {
				q = append(q, Node{node.node.Right, node.idx*2 + 1})
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}