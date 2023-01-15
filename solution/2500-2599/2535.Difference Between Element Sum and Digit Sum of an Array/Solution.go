func differenceOfSum(nums []int) int {
	a, b := 0, 0
	for _, x := range nums {
		a += x
		for ; x > 0; x /= 10 {
			b += x % 10
		}
	}
	return abs(a - b)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}