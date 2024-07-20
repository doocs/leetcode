func bagOfTokensScore(tokens []int, power int) (ans int) {
	sort.Ints(tokens)
	i, j := 0, len(tokens)-1
	score := 0
	for i <= j {
		if power >= tokens[i] {
			power -= tokens[i]
			i++
			score++
			ans = max(ans, score)
		} else if score > 0 {
			power += tokens[j]
			j--
			score--
		} else {
			break
		}
	}
	return
}