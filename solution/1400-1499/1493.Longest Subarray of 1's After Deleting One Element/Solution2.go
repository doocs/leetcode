func longestSubarray(nums []int) (ans int) {
	cnt, j := 0, 0
	for i, x := range nums {
		cnt += x ^ 1
		for ; cnt > 1; j++ {
			cnt -= nums[j] ^ 1
		}
		ans = max(ans, i-j)
	}
	return
}