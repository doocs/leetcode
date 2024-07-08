func minimumDifference(nums []int, k int) int {
	ans := math.MaxInt32
	pre := map[int]bool{}
	for _, x := range nums {
		cur := map[int]bool{x: true}
		for y := range pre {
			cur[x|y] = true
		}
		for y := range cur {
			ans = min(ans, max(y-k, k-y))
		}
		pre = cur
	}
	return ans
}