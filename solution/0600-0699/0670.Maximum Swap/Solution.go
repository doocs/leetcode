func maximumSwap(num int) int {
	s := strconv.Itoa(num)
	chars := []byte(s)
	n := len(chars)
	for i := range chars[:n-1] {
		mx := i + 1
		for j := i + 1; j < n; j++ {
			if chars[j] >= chars[mx] {
				mx = j
			}
		}
		if chars[i] < chars[mx] {
			chars[i], chars[mx] = chars[mx], chars[i]
			break
		}
	}
	ans, _ := strconv.Atoi(string(chars))
	return ans
}