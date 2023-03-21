func convertArray(nums []int) int {
	return min(solve(nums), solve(reverse(nums)))
}

func solve(nums []int) int {
	n := len(nums)
	f := make([][1001]int, n+1)
	for i := 1; i <= n; i++ {
		mi := 1 << 30
		for j := 0; j <= 1000; j++ {
			mi = min(mi, f[i-1][j])
			f[i][j] = mi + abs(nums[i-1]-j)
		}
	}
	ans := 1 << 30
	for _, x := range f[n] {
		ans = min(ans, x)
	}
	return ans
}

func reverse(nums []int) []int {
	for i, j := 0, len(nums)-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	return nums
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}