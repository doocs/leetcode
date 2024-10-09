func longestNiceSubarray(nums []int) (ans int) {
	mask, l := 0, 0
	for r, x := range nums {
		for mask&x != 0 {
			mask ^= nums[l]
			l++
		}
		mask |= x
		ans = max(ans, r-l+1)
	}
	return
}
