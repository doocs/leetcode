func findClosestNumber(nums []int) int {
	ans, d := 0, 1000000
	for _, v := range nums {
		t := abs(v)
		if t < d || (t == d && v > ans) {
			ans, d = v, t
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}