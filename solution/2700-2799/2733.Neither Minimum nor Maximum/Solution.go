func findNonMinOrMax(nums []int) int {
	mi, mx := 100, 0
	for _, x := range nums {
		if x < mi {
			mi = x
		}
		if x > mx {
			mx = x
		}
	}
	for _, x := range nums {
		if x != mi && x != mx {
			return x
		}
	}
	return -1
}