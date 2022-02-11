func characterReplacement(s string, k int) int {
	counter := make([]int, 26)
	j, maxCnt := 0, 0
	for i := range s {
		c := s[i] - 'A'
		counter[c]++
		if maxCnt < counter[c] {
			maxCnt = counter[c]
		}
		if i-j+1 > maxCnt+k {
			counter[s[j]-'A']--
			j++
		}
	}
	return len(s) - j
}