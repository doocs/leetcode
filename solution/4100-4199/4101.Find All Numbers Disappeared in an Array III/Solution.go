func findDisappearedNumbers(nums []int, lower int, upper int) [][]int {
	sort.Ints(nums)
	l := sort.SearchInts(nums, lower)
	r := sort.Search(len(nums), func(i int) bool {
		return nums[i] > upper
	})
	ans := [][]int{}
	pre := lower
	for i := l; i < r; i++ {
		cur := nums[i]
		if pre < cur {
			ans = append(ans, []int{pre, cur - 1})
		}
		pre = cur + 1
	}
	if pre <= upper {
		ans = append(ans, []int{pre, upper})
	}
	return ans
}
