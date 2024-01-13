func longestAlternatingSubarray(nums []int, threshold int) (ans int) {
	n := len(nums)
	for l := range nums {
		if nums[l]%2 == 0 && nums[l] <= threshold {
			r := l + 1
			for r < n && nums[r]%2 != nums[r-1]%2 && nums[r] <= threshold {
				r++
			}
			ans = max(ans, r-l)
		}
	}
	return
}