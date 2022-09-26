func missingTwo(nums []int) []int {
	n := len(nums) + 2
	xor := 0
	for _, v := range nums {
		xor ^= v
	}
	for i := 1; i <= n; i++ {
		xor ^= i
	}
	diff := xor & -xor
	a := 0
	for _, v := range nums {
		if (v & diff) != 0 {
			a ^= v
		}
	}
	for i := 1; i <= n; i++ {
		if (i & diff) != 0 {
			a ^= i
		}
	}
	b := xor ^ a
	return []int{a, b}
}