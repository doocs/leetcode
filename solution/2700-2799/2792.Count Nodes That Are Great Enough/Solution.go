/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countGreatEnoughNodes(root *TreeNode, k int) (ans int) {
	var dfs func(*TreeNode) hp
	dfs = func(root *TreeNode) hp {
		if root == nil {
			return hp{}
		}
		l, r := dfs(root.Left), dfs(root.Right)
		for _, x := range r.IntSlice {
			l.push(x)
			if l.Len() > k {
				l.pop()
			}
		}
		if l.Len() == k && root.Val > l.IntSlice[0] {
			ans++
		}
		l.push(root.Val)
		if l.Len() > k {
			l.pop()
		}
		return l
	}
	dfs(root)
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }