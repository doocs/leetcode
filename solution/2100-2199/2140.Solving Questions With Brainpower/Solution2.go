func mostPoints(questions [][]int) int64 {
	n := len(questions)
	f := make([]int64, n+1)
	for i := n - 1; i >= 0; i-- {
		p := int64(questions[i][0])
		if j := i + questions[i][1] + 1; j <= n {
			p += f[j]
		}
		f[i] = max(f[i+1], p)
	}
	return f[0]
}