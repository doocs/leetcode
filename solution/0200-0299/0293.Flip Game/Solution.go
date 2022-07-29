func generatePossibleNextMoves(currentState string) []string {
	ans := []string{}
	cs := []byte(currentState)
	for i, c := range cs[1:] {
		if c == '+' && cs[i] == '+' {
			cs[i], cs[i+1] = '-', '-'
			ans = append(ans, string(cs))
			cs[i], cs[i+1] = '+', '+'
		}
	}
	return ans
}