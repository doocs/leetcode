func longestEqualSubarray(nums []int, k int) int {
	cnt := map[int]int{}
	mx, l := 0, 0
	for r, x := range nums {
		cnt[x]++
		mx = max(mx, cnt[x])
		if r-l+1-mx > k {
			cnt[nums[l]]--
			l++
		}
	}
	return mx
}