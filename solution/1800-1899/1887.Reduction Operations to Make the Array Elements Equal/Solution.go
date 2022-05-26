func reductionOperations(nums []int) int {
	sort.Ints(nums)
	cnt, res, n := 0, 0, len(nums)
	for i := 1; i < n; i++ {
		if nums[i] != nums[i-1] {
			cnt++
		}
		res += cnt
	}
	return res
}