func checkInclusion(s1 string, s2 string) bool {
	need, window := make(map[byte]int), make(map[byte]int)
	validate, left, right := 0, 0, 0
	for i := range s1 {
		need[s1[i]] += 1
	}
	for ; right < len(s2); right++ {
		c := s2[right]
		window[c] += 1
		if need[c] == window[c] {
			validate++
		}
		for right-left+1 >= len(s1) {
			if validate == len(need) {
				return true
			}
			d := s2[left]
			if need[d] == window[d] {
				validate--
			}
			window[d] -= 1
			left++
		}
	}
	return false
}