/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func closeLampInTree(root *TreeNode) (ans int) {
	const inf = 1 << 30
	var dfs func(*TreeNode) (int, int, int, int)
	dfs = func(root *TreeNode) (int, int, int, int) {
		if root == nil {
			return 0, 0, 0, 0
		}
		l1, l2, l3, l4 := dfs(root.Left)
		r1, r2, r3, r4 := dfs(root.Right)
		t1, t2, t3, t4 := inf, inf, inf, inf
		if root.Val == 1 {
			t1 = min(l1+r1+1, l2+r2+1, l3+r3+1, l4+r4+3)
			t2 = min(l1+r1+2, l2+r2, l3+r3+2, l4+r4+2)
			t3 = min(l1+r1, l2+r2+2, l3+r3+2, l4+r4+2)
			t4 = min(l1+r1+1, l2+r2+1, l3+r3+3, l4+r4+1)
		} else {
			t1 = min(l1+r1, l2+r2+2, l3+r3+2, l4+r4+2)
			t2 = min(l1+r1+1, l2+r2+1, l3+r3+3, l4+r4+1)
			t3 = min(l1+r1+1, l2+r2+1, l3+r3+1, l4+r4+3)
			t4 = min(l1+r1+2, l2+r2, l3+r3+2, l4+r4+2)
		}
		return t1, t2, t3, t4
	}
	ans, _, _, _ = dfs(root)
	return
}

func min(a, b, c, d int) int {
	if b < a {
		a = b
	}
	if c < a {
		a = c
	}
	if d < a {
		a = d
	}
	return a
}