func findErrorNums(nums []int) []int {
	xs := 0
	for i, x := range nums {
		xs ^= x ^ (i + 1)
	}
	lb := xs & -xs
	a := 0
	for i, x := range nums {
		if (i+1)&lb != 0 {
			a ^= (i + 1)
		}
		if x&lb != 0 {
			a ^= x
		}
	}
	b := xs ^ a
	for _, x := range nums {
		if x == a {
			return []int{a, b}
		}
	}
	return []int{b, a}
}