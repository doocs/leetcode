func verifyPostorder(postorder []int) bool {
	if len(postorder) < 2 {
		return true
	}
	var dfs func(i, n int) bool
	dfs = func(i, n int) bool {
		if n <= 0 {
			return true
		}
		v := postorder[i+n-1]
		j := i
		for j < i+n && postorder[j] < v {
			j++
		}
		for k := j; k < i+n; k++ {
			if postorder[k] < v {
				return false
			}
		}
		return dfs(i, j-i) && dfs(j, n+i-j-1)
	}
	return dfs(0, len(postorder))
}