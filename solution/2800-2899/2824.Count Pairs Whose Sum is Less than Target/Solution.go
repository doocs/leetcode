func countPairs(nums []int, target int) (ans int) {
	sort.Ints(nums)
	for j, x := range nums {
		i := sort.SearchInts(nums[:j], target-x)
		ans += i
	}
	return
}