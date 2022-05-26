func getHint(secret string, guess string) string {
	x, y := 0, 0
	cnt1 := make([]int, 10)
	cnt2 := make([]int, 10)
	for i := 0; i < len(secret); i++ {
		a, b := secret[i]-'0', guess[i]-'0'
		if a == b {
			x++
		} else {
			cnt1[a]++
			cnt2[b]++
		}
	}
	for i := 0; i < 10; i++ {
		y += min(cnt1[i], cnt2[i])
	}
	return fmt.Sprintf("%dA%dB", x, y)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}