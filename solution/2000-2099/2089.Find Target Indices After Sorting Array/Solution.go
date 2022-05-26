func targetIndices(nums []int, target int) []int {
	sort.Ints(nums)
	var ans []int
	for i, num := range nums {
		if num == target {
			ans = append(ans, i)
		}
	}
	return ans
}