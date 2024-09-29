func minElement(nums []int) int {
	ans := 100
	for _, x := range nums {
		y := 0
		for ; x > 0; x /= 10 {
			y += x % 10
		}
		ans = min(ans, y)
	}
	return ans
}
