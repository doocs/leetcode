func longestSubarray(nums []int) int {
	mx := 0
	for _, v := range nums {
		mx = max(mx, v)
	}
	ans, cnt := 0, 0
	for _, v := range nums {
		if v == mx {
			cnt++
			ans = max(ans, cnt)
		} else {
			cnt = 0
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}