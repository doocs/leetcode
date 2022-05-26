func reverseWords(s []byte) {
	n := len(s)
	for i, j := 0, 0; j < n; j++ {
		if s[j] == ' ' {
			reverse(s, i, j-1)
			i = j + 1
		} else if j == n-1 {
			reverse(s, i, j)
		}
	}
	reverse(s, 0, n-1)
}

func reverse(s []byte, i, j int) {
	for i < j {
		s[i], s[j] = s[j], s[i]
		i++
		j--
	}
}