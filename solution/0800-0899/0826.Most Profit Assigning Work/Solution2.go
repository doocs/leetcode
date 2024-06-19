func maxProfitAssignment(difficulty []int, profit []int, worker []int) (ans int) {
	m := slices.Max(difficulty)
	f := make([]int, m+1)
	for i, d := range difficulty {
		f[d] = max(f[d], profit[i])
	}
	for i := 1; i <= m; i++ {
		f[i] = max(f[i], f[i-1])
	}
	for _, w := range worker {
		ans += f[min(w, m)]
	}
	return
}
