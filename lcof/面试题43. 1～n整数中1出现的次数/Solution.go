func countDigitOne(n int) int {
	a := [12]int{}
	f := [12][12]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	i := -1
	for ; n > 0; n /= 10 {
		i++
		a[i] = n % 10
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, cnt int, limit bool) int {
		if pos < 0 {
			return cnt
		}
		if !limit && f[pos][cnt] != -1 {
			return f[pos][cnt]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			t := 0
			if i == 1 {
				t++
			}
			ans += dfs(pos-1, cnt+t, limit && i == up)
		}
		f[pos][cnt] = ans
		return ans
	}
	return dfs(i, 0, true)
}