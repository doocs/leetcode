func maxSubarrayLength(nums []int, k int) (ans int) {
	cnt := make(map[int]int)
	for l, r := 0, 0; r < len(nums); r++ {
		cnt[nums[r]]++
		for cnt[nums[r]] > k {
			cnt[nums[l]]--
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
