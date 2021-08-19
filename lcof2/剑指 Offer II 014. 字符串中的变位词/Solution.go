func checkInclusion(s1 string, s2 string) bool {
	n1, n2 := len(s1), len(s2)
	if n1 > n2 {
		return false
	}
	window := make([]int, 26)
	for i := 0; i < n1; i++ {
		window[s1[i]-'a']++
		window[s2[i]-'a']--
	}
	if check(window) {
		return true
	}
	for i := n1; i < n2; i++ {
		window[s2[i]-'a']--
		window[s2[i-n1]-'a']++
		if check(window) {
			return true
		}
	}
	return false
}

func check(window []int) bool {
	for _, cnt := range window {
		if cnt != 0 {
			return false
		}
	}
	return true
}
