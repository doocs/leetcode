func longestSubarray(nums []int) int {
	mx := slices.Max(nums)
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