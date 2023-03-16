func answerQueries(nums []int, queries []int) (ans []int) {
	sort.Ints(nums)
	for i := 1; i < len(nums); i++ {
		nums[i] += nums[i-1]
	}
	for _, q := range queries {
		ans = append(ans, sort.SearchInts(nums, q+1))
	}
	return
}