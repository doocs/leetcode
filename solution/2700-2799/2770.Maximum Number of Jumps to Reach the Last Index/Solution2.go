func maximumJumps(nums []int, target int) int {
	n := len(nums)
	f := make([]int, n)
	for i := range f {
		f[i] = -(1 << 30)
	}
	f[n-1] = 0
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if abs(nums[i]-nums[j]) <= target {
				f[i] = max(f[i], 1+f[j])
			}
		}
	}
	if f[0] < 0 {
		return -1
	}
	return f[0]
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
