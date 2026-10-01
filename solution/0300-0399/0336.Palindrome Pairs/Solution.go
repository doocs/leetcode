func palindromePairs(words []string) [][]int {
	d := map[string]int{}
	for i, w := range words {
		d[w] = i
	}
	var ans [][]int
	for i, w := range words {
		for j := 0; j <= len(w); j++ {
			a, b := w[:j], w[j:]
			ra, rb := reverse(a), reverse(b)
			if k, ok := d[ra]; ok && k != i && b == rb {
				ans = append(ans, []int{i, k})
			}
			if j > 0 {
				if k, ok := d[rb]; ok && k != i && a == ra {
					ans = append(ans, []int{k, i})
				}
			}
		}
	}
	return ans
}

func reverse(s string) string {
	b := []byte(s)
	for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
	return string(b)
}
