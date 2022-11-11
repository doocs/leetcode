func permute(nums []int) (ans [][]int) {
	n := len(nums)
	t := make([]int, n)
	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			cp := make([]int, n)
			copy(cp, t)
			ans = append(ans, cp)
			return
		}
		for j, v := range nums {
			if !vis[j] {
				vis[j] = true
				t[i] = v
				dfs(i + 1)
				vis[j] = false
			}
		}
	}
	dfs(0)
	return
}