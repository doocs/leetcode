func longestAlternatingSubarray(nums []int, threshold int) (ans int) {
	for l, n := 0, len(nums); l < n; {
		if nums[l]%2 == 0 && nums[l] <= threshold {
			r := l + 1
			for r < n && nums[r]%2 != nums[r-1]%2 && nums[r] <= threshold {
				r++
			}
			ans = max(ans, r-l)
			l = r
		} else {
			l++
		}
	}
	return
}