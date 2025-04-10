func makeArrayPositive(nums []int) (ans int) {
	l := -1
	preMx := 0
	s := 0
	for r, x := range nums {
		s += x
		if r-l > 2 && s <= preMx {
			ans++
			l = r
			preMx = 0
			s = 0
		} else if r-l >= 2 {
			preMx = max(preMx, s-x-nums[r-1])
		}
	}
	return
}