func longestNiceSubarray(nums []int) int {
	t, j := 0, 0
	ans := 0
	for i, v := range nums {
		for (t & v) != 0 {
			t ^= nums[j]
			j++
		}
		t |= v
		ans = max(ans, i-j+1)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}