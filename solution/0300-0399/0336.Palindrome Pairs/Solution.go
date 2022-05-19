func palindromePairs(words []string) [][]int {
	base := 131
	mod := int(1e9) + 7
	mul := make([]int, 310)
	mul[0] = 1
	for i := 1; i < len(mul); i++ {
		mul[i] = (mul[i-1] * base) % mod
	}
	n := len(words)
	prefix := make([]int, n)
	suffix := make([]int, n)
	for i, word := range words {
		m := len(word)
		for j, c := range word {
			t := int(c-'a') + 1
			s := int(word[m-j-1]-'a') + 1
			prefix[i] = (prefix[i]*base)%mod + t
			suffix[i] = (suffix[i]*base)%mod + s
		}
	}
	check := func(i, j, n, m int) bool {
		t := ((prefix[i]*mul[n])%mod + prefix[j]) % mod
		s := ((suffix[j]*mul[m])%mod + suffix[i]) % mod
		return t == s
	}
	var ans [][]int
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if check(i, j, len(words[j]), len(words[i])) {
				ans = append(ans, []int{i, j})
			}
			if check(j, i, len(words[i]), len(words[j])) {
				ans = append(ans, []int{j, i})
			}
		}
	}
	return ans
}