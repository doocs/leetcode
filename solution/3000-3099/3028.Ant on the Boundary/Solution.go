func returnToBoundaryCount(nums []int) (ans int) {
	s := 0
	for _, x := range nums {
		s += x
		if s == 0 {
			ans++
		}
	}
	return
}