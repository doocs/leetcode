func kthCharacter(s string, k int64) byte {
	for _, w := range strings.Split(s, " ") {
		m := (1 + int64(len(w))) * int64(len(w)) / 2
		if k == m {
			return ' '
		}
		if k > m {
			k -= m + 1
		} else {
			var cur int64
			for i := 0; ; i++ {
				cur += int64(i + 1)
				if k < cur {
					return w[i]
				}
			}
		}
	}
	return ' '
}
