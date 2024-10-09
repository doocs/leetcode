func canAliceWin(nums []int) bool {
	a, b := 0, 0
	for _, x := range nums {
		if x < 10 {
			a += x
		} else {
			b += x
		}
	}
	return a != b
}