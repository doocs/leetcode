func minimumDifference(nums []int, k int) int {
	ans := abs(nums[0] - k)
	pre := map[int]bool{nums[0]: true}
	for _, x := range nums {
		cur := map[int]bool{x: true}
		for y := range pre {
			cur[x&y] = true
		}
		for y := range cur {
			ans = min(ans, abs(y-k))
		}
		pre = cur
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}