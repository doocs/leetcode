func divisibilityArray(word string, m int) (ans []int) {
	x := 0
	for _, c := range word {
		x = (x*10 + int(c-'0')) % m
		if x == 0 {
			ans = append(ans, 1)
		} else {
			ans = append(ans, 0)
		}
	}
	return ans
}