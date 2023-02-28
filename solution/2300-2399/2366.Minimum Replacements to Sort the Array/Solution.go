func minimumReplacement(nums []int) (ans int64) {
	n := len(nums)
	mx := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i] <= mx {
			mx = nums[i]
			continue
		}
		k := (nums[i] + mx - 1) / mx
		ans += int64(k - 1)
		mx = nums[i] / k
	}
	return
}