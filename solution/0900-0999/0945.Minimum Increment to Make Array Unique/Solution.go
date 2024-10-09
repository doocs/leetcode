func minIncrementForUnique(nums []int) (ans int) {
	sort.Ints(nums)
	y := -1
	for _, x := range nums {
		y = max(y+1, x)
		ans += y - x
	}
	return
}