func countPoints(rings string) int {
	mp := make(map[byte]map[byte]bool)
	for i := 1; i < len(rings); i += 2 {
		c := rings[i]
		if len(mp[c]) == 0 {
			mp[c] = make(map[byte]bool)
		}
		mp[c][rings[i-1]] = true
	}
	ans := 0
	for _, v := range mp {
		if len(v) == 3 {
			ans++
		}
	}
	return ans
}