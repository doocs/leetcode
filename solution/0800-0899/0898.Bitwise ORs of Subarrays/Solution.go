func subarrayBitwiseORs(arr []int) int {
	ans := map[int]bool{}
	s := map[int]bool{0: true}
	for _, x := range arr {
		t := map[int]bool{x: true}
		for y := range s {
			t[x|y] = true
		}
		s = t
		for y := range s {
			ans[y] = true
		}
	}
	return len(ans)
}