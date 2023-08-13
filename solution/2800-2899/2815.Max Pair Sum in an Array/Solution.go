func maxSum(nums []int) int {
	ans := -1
	f := func(x int) int {
		y := 0
		for ; x > 0; x /= 10 {
			y = max(y, x%10)
		}
		return y
	}
	for i, x := range nums {
		for _, y := range nums[i+1:] {
			if v := x + y; ans < v && f(x) == f(y) {
				ans = v
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}