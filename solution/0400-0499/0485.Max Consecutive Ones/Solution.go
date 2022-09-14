func findMaxConsecutiveOnes(nums []int) int {
	ans, cnt := 0, 0
	for _, v := range nums {
		if v == 1 {
			cnt++
		} else {
			ans = max(ans, cnt)
			cnt = 0
		}
	}
	return max(ans, cnt)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}