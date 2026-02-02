func reverseByType(s string) string {
	a := make([]byte, 0)
	b := make([]byte, 0)
	t := []byte(s)

	for _, c := range t {
		if (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') {
			a = append(a, c)
		} else {
			b = append(b, c)
		}
	}

	j, k := len(a), len(b)
	for i := 0; i < len(t); i++ {
		if (t[i] >= 'A' && t[i] <= 'Z') || (t[i] >= 'a' && t[i] <= 'z') {
			j--
			t[i] = a[j]
		} else {
			k--
			t[i] = b[k]
		}
	}

	return string(t)
}
