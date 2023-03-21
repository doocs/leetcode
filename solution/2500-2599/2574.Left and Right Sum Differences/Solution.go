func leftRigthDifference(nums []int) (ans []int) {
	var left, right int
	for _, x := range nums {
		right += x
	}
	for _, x := range nums {
		right -= x
		ans = append(ans, abs(left-right))
		left += x
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}