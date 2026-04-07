func leftRightDifference(nums []int) []int {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	n := len(nums)
	ans := make([]int, n)
	for i, x := range nums {
		r -= x
		ans[i] = abs(l - r)
		l += x
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
