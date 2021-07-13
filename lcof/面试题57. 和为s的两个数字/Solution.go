func twoSum(nums []int, target int) []int {
	for p, q := 0, len(nums)-1; p < q; {
		s := nums[p] + nums[q]
		if s == target {
			return []int{nums[p], nums[q]}
		}
		if s < target {
			p++
		} else {
			q--
		}
	}
	return nil
}