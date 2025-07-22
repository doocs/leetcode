func permute(nums []int) (ans [][]int) {
	n := len(nums)
	t := make([]int, n)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans = append(ans, slices.Clone(t))
			return
		}
		for j, x := range nums {
			if !vis[j] {
				vis[j] = true
				t[i] = x
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}
