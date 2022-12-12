func masterMind(solution string, guess string) []int {
	var x, y int
	cnt1 := map[byte]int{}
	cnt2 := map[byte]int{}
	for i := range solution {
		a, b := solution[i], guess[i]
		if a == b {
			x++
		}
		cnt1[a]++
		cnt2[b]++
	}
	for _, c := range []byte("RYGB") {
		y += min(cnt1[c], cnt2[c])
	}
	return []int{x, y - x}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}