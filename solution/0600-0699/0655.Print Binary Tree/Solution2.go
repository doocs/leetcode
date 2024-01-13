/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func printTree(root *TreeNode) [][]string {
	h := height(root)
	m, n := h+1, (1<<(h+1))-1
	ans := make([][]string, m)
	for i := range ans {
		ans[i] = make([]string, n)
		for j := range ans[i] {
			ans[i][j] = ""
		}
	}
	q := []tuple{tuple{root, 0, (n - 1) / 2}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		root := p.node
		r, c := p.r, p.c
		ans[r][c] = strconv.Itoa(root.Val)
		if root.Left != nil {
			q = append(q, tuple{root.Left, r + 1, c - int(math.Pow(float64(2), float64(h-r-1)))})
		}
		if root.Right != nil {
			q = append(q, tuple{root.Right, r + 1, c + int(math.Pow(float64(2), float64(h-r-1)))})
		}
	}
	return ans
}

func height(root *TreeNode) int {
	h := -1
	q := []*TreeNode{root}
	for len(q) > 0 {
		h++
		for n := len(q); n > 0; n-- {
			root := q[0]
			q = q[1:]
			if root.Left != nil {
				q = append(q, root.Left)
			}
			if root.Right != nil {
				q = append(q, root.Right)
			}
		}
	}
	return h
}

type tuple struct {
	node *TreeNode
	r    int
	c    int
}