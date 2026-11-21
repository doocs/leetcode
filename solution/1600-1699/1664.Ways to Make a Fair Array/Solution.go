func waysToMakeFair(nums []int) int {
	s, r0 := 0, 0
	for i, x := range nums {
		s += x
		if i%2 == 0 {
			r0 += x
		}
	}
	r := [2]int{r0, s - r0}
	l := [2]int{}
	ans := 0
	for i, x := range nums {
		r[i%2] -= x
		if l[0]+r[1] == l[1]+r[0] {
			ans++
		}
		l[i%2] += x
	}
	return ans
}
