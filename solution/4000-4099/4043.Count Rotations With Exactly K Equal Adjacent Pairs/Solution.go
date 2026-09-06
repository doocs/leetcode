func countRotations(s string, k int) int {
	n := len(s)
	score := 0

	for i := 0; i < n-1; i++ {
		if s[i] == s[i+1] {
			score++
		}
	}

	ans := 0
	if score == k {
		ans++
	}

	for i := n; i < n*2-1; i++ {
		if s[i%n] == s[(i-1)%n] {
			score++
		}
		if s[i%n] == s[(i+1)%n] {
			score--
		}
		if score == k {
			ans++
		}
	}

	return ans
}
