/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func verticalOrder(root *TreeNode) [][]int {
	d := map[int][][]int{}
	var dfs func(*TreeNode, int, int)
	dfs = func(root *TreeNode, depth, offset int) {
		if root == nil {
			return
		}
		d[offset] = append(d[offset], []int{depth, root.Val})
		dfs(root.Left, depth+1, offset-1)
		dfs(root.Right, depth+1, offset+1)
	}
	dfs(root, 0, 0)
	idx := []int{}
	for i := range d {
		idx = append(idx, i)
	}
	sort.Ints(idx)
	ans := [][]int{}
	for _, i := range idx {
		v := d[i]
		sort.SliceStable(v, func(i, j int) bool { return v[i][0] < v[j][0] })
		t := []int{}
		for _, x := range v {
			t = append(t, x[1])
		}
		ans = append(ans, t)
	}
	return ans
}