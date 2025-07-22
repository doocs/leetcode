func maxDistinctElements(nums []int, k int) (ans int) {
	sort.Ints(nums)
	pre := math.MinInt32
	for _, x := range nums {
		cur := min(x+k, max(x-k, pre+1))
		if cur > pre {
			ans++
			pre = cur
		}
	}
	return
}
