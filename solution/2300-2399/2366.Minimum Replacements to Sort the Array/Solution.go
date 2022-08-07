func minimumReplacement(nums []int) int64 {
	var ans int64
	n := len(nums)
	mi := nums[n-1]
	for i := n - 2; i >= 0; i-- {
		v := nums[i]
		if v <= mi {
			mi = v
			continue
		}
		k := (v + mi - 1) / mi
		ans += int64(k - 1)
		mi = v / k
	}
	return ans
}