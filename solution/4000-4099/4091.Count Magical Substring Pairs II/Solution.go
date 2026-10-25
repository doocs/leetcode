func calc(s string) [][]int64 {
	n := len(s)
	f := make([][]int64, n)
	for i := range f {
		f[i] = make([]int64, 27)
	}
	for k := 1; k <= 26; k++ {
		var cnt [26]int
		l, distinct := 0, 0
		for r := 0; r < n; r++ {
			c := s[r] - 'a'
			if cnt[c] == 0 {
				distinct++
			}
			cnt[c]++
			for distinct > k {
				c = s[l] - 'a'
				cnt[c]--
				if cnt[c] == 0 {
					distinct--
				}
				l++
			}
			f[r][k] = int64(r - l + 1)
		}
	}
	return f
}

func countMagicalPairs(s string) int64 {
	n := len(s)
	left := calc(s)
	b := []byte(s)
	for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
	right := calc(string(b))
	var ans int64
	for j := 0; j < n-1; j++ {
		r := n - 2 - j
		for k := 1; k <= 26; k++ {
			ans += (left[j][k] - left[j][k-1]) *
				(right[r][k] - right[r][k-1])
		}
	}
	return ans
}
