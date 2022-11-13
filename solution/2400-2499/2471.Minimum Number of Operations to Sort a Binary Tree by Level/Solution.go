/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func minimumOperations(root *TreeNode) (ans int) {
	f := func(t []int) int {
		var alls []int
		for _, v := range t {
			alls = append(alls, v)
		}
		sort.Ints(alls)
		m := make(map[int]int)
		for i, v := range alls {
			m[v] = i
		}
		for i := range t {
			t[i] = m[t[i]]
		}
		res := 0
		for i := range t {
			for t[i] != i {
				t[i], t[t[i]] = t[t[i]], t[i]
				res++
			}
		}
		return res
	}

	q := []*TreeNode{root}
	for len(q) > 0 {
		t := []int{}
		for n := len(q); n > 0; n-- {
			node := q[0]
			q = q[1:]
			t = append(t, node.Val)
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans += f(t)
	}
	return
}