func countValidSelections(nums []int) (ans int) {
	l, s := 0, 0
	for _, x := range nums {
		s += x
	}
	for _, x := range nums {
		if x != 0 {
			l += x
		} else if l*2 == s {
			ans += 2
		} else if abs(l*2-s) <= 1 {
			ans++
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
