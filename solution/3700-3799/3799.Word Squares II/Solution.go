func wordSquares(words []string) [][]string {
	sort.Strings(words)
	n := len(words)
	ans := [][]string{}

	for i := 0; i < n; i++ {
		top := words[i]
		for j := 0; j < n; j++ {
			if j != i {
				left := words[j]
				for k := 0; k < n; k++ {
					if k != j && k != i {
						right := words[k]
						for h := 0; h < n; h++ {
							if h != k && h != j && h != i {
								bottom := words[h]
								if top[0] == left[0] &&
									top[3] == right[0] &&
									bottom[0] == left[3] &&
									bottom[3] == right[3] {
									ans = append(ans, []string{top, left, right, bottom})
								}
							}
						}
					}
				}
			}
		}
	}
	return ans
}
