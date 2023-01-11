func maximumSum(nums []int) int {
	ans := -1
	d := [100]int{}
	for _, v := range nums {
		y := 0
		for x := v; x > 0; x /= 10 {
			y += x % 10
		}
		if d[y] > 0 {
			ans = max(ans, d[y]+v)
		}
		d[y] = max(d[y], v)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}