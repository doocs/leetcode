func maxProduct(nums []int) int {
	a, b := 0, 0
	for _, v := range nums {
		if v > a {
			b, a = a, v
		} else if v > b {
			b = v
		}
	}
	return (a - 1) * (b - 1)
}