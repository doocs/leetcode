func garbageCollection(garbage []string, travel []int) int {
	ans := 0
	pos := map[rune]int{}
	for i, v := range garbage {
		ans += len(v)
		for _, c := range v {
			pos[c] = i
		}
	}
	s := make([]int, len(travel)+1)
	for i, v := range travel {
		s[i+1] = s[i] + v
	}
	for _, i := range pos {
		ans += s[i]
	}
	return ans
}