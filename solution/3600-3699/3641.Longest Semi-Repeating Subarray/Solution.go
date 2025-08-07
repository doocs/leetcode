func longestSubarray(nums []int, k int) (ans int) {
	cnt := make(map[int]int)
	cur, l := 0, 0
	for r := 0; r < len(nums); r++ {
		if cnt[nums[r]]++; cnt[nums[r]] == 2 {
			cur++
		}
		for cur > k {
			if cnt[nums[l]]--; cnt[nums[l]] == 1 {
				cur--
			}
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
