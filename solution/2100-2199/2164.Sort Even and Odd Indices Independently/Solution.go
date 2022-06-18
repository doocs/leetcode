func sortEvenOdd(nums []int) []int {
	n := len(nums)
	var a []int
	var b []int
	for i, v := range nums {
		if i%2 == 0 {
			a = append(a, v)
		} else {
			b = append(b, v)
		}
	}
	ans := make([]int, n)
	sort.Ints(a)
	sort.Slice(b, func(i, j int) bool {
		return b[i] > b[j]
	})
	for i, j := 0, 0; j < len(a); i, j = i+2, j+1 {
		ans[i] = a[j]
	}
	for i, j := 1, 0; j < len(b); i, j = i+2, j+1 {
		ans[i] = b[j]
	}
	return ans
}