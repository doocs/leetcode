func calculateScore(instructions []string, values []int) (ans int64) {
	n := len(values)
	vis := make([]bool, n)
	i := 0
	for i >= 0 && i < n && !vis[i] {
		vis[i] = true
		if instructions[i][0] == 'a' {
			ans += int64(values[i])
			i += 1
		} else {
			i += values[i]
		}
	}
	return
}
