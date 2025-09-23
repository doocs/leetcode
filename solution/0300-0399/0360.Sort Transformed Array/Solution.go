func sortTransformedArray(nums []int, a int, b int, c int) []int {
	f := func(x int) int {
		return a*x*x + b*x + c
	}

	n := len(nums)
	ans := make([]int, n)
	i, j := 0, n-1

	for k := 0; k < n; k++ {
		y1, y2 := f(nums[i]), f(nums[j])
		if a > 0 {
			if y1 > y2 {
				ans[n-k-1] = y1
				i++
			} else {
				ans[n-k-1] = y2
				j--
			}
		} else {
			if y1 > y2 {
				ans[k] = y2
				j--
			} else {
				ans[k] = y1
				i++
			}
		}
	}
	return ans
}
