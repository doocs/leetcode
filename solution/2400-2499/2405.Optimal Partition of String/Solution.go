func partitionString(s string) int {
	ss := map[rune]bool{}
	ans := 1
	for _, c := range s {
		if ss[c] {
			ans++
			ss = map[rune]bool{}
		}
		ss[c] = true
	}
	return ans
}