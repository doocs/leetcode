func reductionOperations(nums []int) int {
	sort.Ints(nums)
	ans, cnt := 0, 0
	for i, v := range nums[1:] {
		if v != nums[i] {
			cnt++
		}
		ans += cnt
	}
	return ans
}