func maximumJumps(nums []int, target int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i == n-1 {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		f[i] = -(1 << 30)
		for j := i + 1; j < n; j++ {
			if abs(nums[i]-nums[j]) <= target {
				f[i] = max(f[i], 1+dfs(j))
			}
		}
		return f[i]
	}
	ans := dfs(0)
	if ans < 0 {
		return -1
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}