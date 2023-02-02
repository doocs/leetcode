func verifyPostorder(postorder []int) bool {
	var dfs func(l, r int) bool
	dfs = func(l, r int) bool {
		if l >= r {
			return true
		}
		v := postorder[r]
		i := l
		for i < r && postorder[i] < v {
			i++
		}
		for j := i; j < r; j++ {
			if postorder[j] < v {
				return false
			}
		}
		return dfs(l, i-1) && dfs(i, r-1)
	}
	return dfs(0, len(postorder)-1)
}