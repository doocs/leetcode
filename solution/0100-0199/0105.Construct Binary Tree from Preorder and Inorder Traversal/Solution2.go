func getBinaryTrees(preOrder []int, inOrder []int) []*TreeNode {
	n := len(preOrder)
	d := map[int][]int{}
	for i, x := range inOrder {
		d[x] = append(d[x], i)
	}
	var dfs func(i, j, n int) []*TreeNode
	dfs = func(i, j, n int) []*TreeNode {
		ans := []*TreeNode{}
		if n <= 0 {
			ans = append(ans, nil)
			return ans
		}
		v := preOrder[i]
		for _, k := range d[v] {
			if k >= j && k < j+n {
				lefts := dfs(i+1, j, k-j)
				rights := dfs(i+1+k-j, k+1, n-1-(k-j))
				for _, left := range lefts {
					for _, right := range rights {
						ans = append(ans, &TreeNode{v, left, right})
					}
				}
			}
		}
		return ans
	}
	return dfs(0, 0, n)
}