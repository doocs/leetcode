func countConsistentStrings(allowed string, words []string) int {
	chars := [26]bool{}
	for _, c := range allowed {
		chars[c-'a'] = true
	}
	res := 0
	for _, word := range words {
		find := true
		for _, c := range word {
			if !chars[c-'a'] {
				find = false
				break
			}
		}
		if find {
			res++
		}
	}
	return res
}