func permute(nums []int) [][]int {
	n := len(nums)
	res := make([][]int, 0)
	path := make([]int, n)
	used := make([]bool, n)
	dfs(0, n, nums, used, path, &res)
	return res
}

func dfs(u, n int, nums []int, used []bool, path []int, res *[][]int) {
	if u == n {
		t := make([]int, n)
		copy(t, path)
		*res = append(*res, t)
		return
	}
	for i := 0; i < n; i++ {
		if !used[i] {
			path[u] = nums[i]
			used[i] = true
			dfs(u+1, n, nums, used, path, res)
			used[i] = false
		}
	}
}