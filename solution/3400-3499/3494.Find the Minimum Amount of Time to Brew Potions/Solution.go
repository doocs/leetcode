func minTime(skill []int, mana []int) int64 {
	n := len(skill)
	f := make([]int64, n)
	for _, x := range mana {
		var tot int64
		for i := 0; i < n; i++ {
			tot = max(tot, f[i]) + int64(skill[i])*int64(x)
		}
		f[n-1] = tot
		for i := n - 2; i >= 0; i-- {
			f[i] = f[i+1] - int64(skill[i+1])*int64(x)
		}
	}
	return f[n-1]
}
