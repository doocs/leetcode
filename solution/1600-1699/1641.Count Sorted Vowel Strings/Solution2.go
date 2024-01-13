func countVowelStrings(n int) (ans int) {
	f := [5]int{1, 1, 1, 1, 1}
	for i := 0; i < n-1; i++ {
		s := 0
		for j := 0; j < 5; j++ {
			s += f[j]
			f[j] = s
		}
	}
	for _, v := range f {
		ans += v
	}
	return
}