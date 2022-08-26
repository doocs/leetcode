func countElements(arr []int) int {
	s := map[int]bool{}
	for _, x := range arr {
		s[x] = true
	}
	ans := 0
	for _, x := range arr {
		if s[x+1] {
			ans++
		}
	}
	return ans
}