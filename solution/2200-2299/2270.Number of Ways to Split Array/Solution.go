func waysToSplitArray(nums []int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	ans, t := 0, 0
	for _, v := range nums[:len(nums)-1] {
		t += v
		if t >= s-t {
			ans++
		}
	}
	return ans
}