func generateString(s string, t string) string {
	n, m := len(s), len(t)
	ans := make([]byte, n+m-1)
	fixed := make([]bool, n+m-1)

	for i := range ans {
		ans[i] = 'a'
	}

	for i, b := range s {
		if b != 'T' {
			continue
		}
		for j, c := range t {
			k := i + j
			if fixed[k] && ans[k] != byte(c) {
				return ""
			}
			ans[k] = byte(c)
			fixed[k] = true
		}
	}

	for i, b := range s {
		if b != 'F' {
			continue
		}

		same := true
		for j := 0; j < m; j++ {
			if ans[i+j] != t[j] {
				same = false
				break
			}
		}
		if !same {
			continue
		}

		ok := false
		for j := i + m - 1; j >= i; j-- {
			if !fixed[j] {
				ans[j] = 'b'
				ok = true
				break
			}
		}
		if !ok {
			return ""
		}
	}

	return string(ans)
}
