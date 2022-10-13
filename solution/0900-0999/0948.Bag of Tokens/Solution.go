func bagOfTokensScore(tokens []int, power int) int {
	sort.Ints(tokens)
	i, j := 0, len(tokens)-1
	ans, t := 0, 0
	for i <= j {
		if power >= tokens[i] {
			power -= tokens[i]
			i, t = i+1, t+1
			ans = max(ans, t)
		} else if t > 0 {
			power += tokens[j]
			j, t = j-1, t-1
		} else {
			break
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}