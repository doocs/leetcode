func maximumSum(nums []int) int {
	d := [100]int{}
	ans := -1
	for _, v := range nums {
		x := 0
		for y := v; y > 0; y /= 10 {
			x += y % 10
		}
		if d[x] > 0 {
			ans = max(ans, d[x]+v)
		}
		d[x] = max(d[x], v)
	}
	return ans
}