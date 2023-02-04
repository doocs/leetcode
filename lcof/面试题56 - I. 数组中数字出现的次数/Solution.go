func singleNumbers(nums []int) []int {
	xs := 0
	for _, x := range nums {
		xs ^= x
	}
	lb := xs & -xs
	a := 0
	for _, x := range nums {
		if x&lb != 0 {
			a ^= x
		}
	}
	b := xs ^ a
	return []int{a, b}
}