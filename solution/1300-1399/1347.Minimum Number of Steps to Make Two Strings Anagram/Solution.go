func minSteps(s string, t string) int {
	counter := make([]int, 26)
	for _, c := range s {
		counter[c-'a']++
	}
	res := 0
	for _, c := range t {
		if counter[c-'a'] > 0 {
			counter[c-'a']--
		} else {
			res++
		}
	}
	return res
}