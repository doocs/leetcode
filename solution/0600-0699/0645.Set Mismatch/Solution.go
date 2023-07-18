func findErrorNums(nums []int) []int {
	eor := 0
	for i, x := range nums {
		eor ^= x ^ (i + 1)
	}
	lb := eor & -eor
	a := 0
	for i, x := range nums {
		if (i+1)&lb != 0 {
			a ^= (i + 1)
		}
		if x&lb != 0 {
			a ^= x
		}
	}
	b := eor ^ a
	for _, x := range nums {
		if x == a {
			return []int{a, b}
		}
	}
	return []int{b, a}
}