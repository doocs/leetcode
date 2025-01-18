func canBeIncreasing(nums []int) bool {
	check := func(k int) bool {
		pre := 0
		for i, x := range nums {
			if i == k {
				continue
			}
			if pre >= x {
				return false
			}
			pre = x
		}
		return true
	}
	i := 0
	for i+1 < len(nums) && nums[i] < nums[i+1] {
		i++
	}
	return check(i) || check(i+1)
}
