func smallestSubarrays(nums []int) []int {
	n := len(nums)
	f := make([]int, 32)
	for i := range f {
		f[i] = -1
	}
	ans := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		t := 1
		for j := 0; j < 32; j++ {
			if ((nums[i] >> j) & 1) == 1 {
				f[j] = i
			} else if f[j] != -1 {
				t = max(t, f[j]-i+1)
			}
		}
		ans[i] = t
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}