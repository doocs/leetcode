func maximumStrongPairXor(nums []int) (ans int) {
	for _, x := range nums {
		for _, y := range nums {
			if abs(x-y) <= min(x, y) {
				ans = max(ans, x^y)
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}