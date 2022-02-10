func maxNumberOfBalloons(text string) int {
	counter := make([]int, 26)
	for i := range text {
		counter[text[i]-'a']++
	}
	counter['l'-'a'] >>= 1
	counter['o'-'a'] >>= 1
	ans := 10000
	t := "balon"
	for i := range t {
		ans = min(ans, counter[t[i]-'a'])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}