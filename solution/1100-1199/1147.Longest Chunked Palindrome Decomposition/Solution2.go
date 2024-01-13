func longestDecomposition(text string) (ans int) {
	for i, j := 0, len(text)-1; i <= j; {
		ok := false
		for k := 1; i+k-1 < j-k+1; k++ {
			if text[i:i+k] == text[j-k+1:j+1] {
				ans += 2
				i += k
				j -= k
				ok = true
				break
			}
		}
		if !ok {
			ans++
			break
		}
	}
	return
}