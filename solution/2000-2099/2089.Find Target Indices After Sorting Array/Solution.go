func targetIndices(nums []int, target int) (ans []int) {
	sort.Ints(nums)
	for i, v := range nums {
		if v == target {
			ans = append(ans, i)
		}
	}
	return
}