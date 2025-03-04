func reductionOperations(nums []int) (ans int) {
	sort.Ints(nums)
	cnt := 0
	for i, x := range nums[1:] {
		if x != nums[i] {
			cnt++
		}
		ans += cnt
	}
	return
}
