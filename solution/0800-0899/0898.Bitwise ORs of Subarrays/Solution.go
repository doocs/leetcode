func subarrayBitwiseORs(arr []int) int {
	ans := map[int]bool{}
	s := map[int]bool{}
	for _, x := range arr {
		t := map[int]bool{x: true}
		for y := range s {
			t[x|y] = true
		}
		for y := range t {
			ans[y] = true
		}
		s = t
	}
	return len(ans)
}