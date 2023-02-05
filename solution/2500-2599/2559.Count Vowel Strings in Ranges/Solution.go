func vowelStrings(words []string, queries [][]int) (ans []int) {
	vowels := "aeiou"
	t := []int{}
	for i, w := range words {
		if strings.Contains(vowels, w[:1]) && strings.Contains(vowels, w[len(w)-1:]) {
			t = append(t, i)
		}
	}
	for _, q := range queries {
		i := sort.Search(len(t), func(i int) bool { return t[i] >= q[0] })
		j := sort.Search(len(t), func(i int) bool { return t[i] >= q[1]+1 })
		ans = append(ans, j-i)
	}
	return
}