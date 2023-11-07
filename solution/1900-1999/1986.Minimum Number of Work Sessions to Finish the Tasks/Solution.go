func minSessions(tasks []int, sessionTime int) int {
	n := len(tasks)
	ok := make([]bool, 1<<n)
	f := make([]int, 1<<n)
	for i := 1; i < 1<<n; i++ {
		t := 0
		f[i] = 1 << 30
		for j, x := range tasks {
			if i>>j&1 == 1 {
				t += x
			}
		}
		ok[i] = t <= sessionTime
	}
	for i := 1; i < 1<<n; i++ {
		for j := i; j > 0; j = (j - 1) & i {
			if ok[j] {
				f[i] = min(f[i], f[i^j]+1)
			}
		}
	}
	return f[1<<n-1]
}