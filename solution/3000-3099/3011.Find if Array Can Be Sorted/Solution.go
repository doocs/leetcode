func canSortArray(nums []int) bool {
	preMx := -300
	i, n := 0, len(nums)
	for i < n {
		j := i + 1
		cnt := bits.OnesCount(uint(nums[i]))
		mi, mx := nums[i], nums[i]
		for j < n && bits.OnesCount(uint(nums[j])) == cnt {
			mi = min(mi, nums[j])
			mx = max(mx, nums[j])
			j++
		}
		if preMx > mi {
			return false
		}
		preMx = mx
		i = j
	}
	return true
}