func shortestMatchingSubstring(s string, p string) int {
	star := 0
	for p[star] != '*' {
		star++
	}
	star2 := star + 1
	for p[star2] != '*' {
		star2++
	}
	a, b, c := p[:star], p[star+1:star2], p[star2+1:]
	A, B, C := matchStarts(s, a), matchStarts(s, b), matchStarts(s, c)
	ans := len(s) + 1
	j, k := 0, 0
	for _, i := range A {
		for j < len(B) && B[j] < i+len(a) {
			j++
		}
		if j == len(B) {
			break
		}
		for k < len(C) && C[k] < B[j]+len(b) {
			k++
		}
		if k == len(C) {
			break
		}
		ans = min(ans, C[k]+len(c)-i)
	}
	if ans > len(s) {
		return -1
	}
	return ans
}

func matchStarts(s, pat string) []int {
	n := len(s)
	if pat == "" {
		res := make([]int, n+1)
		for i := 0; i <= n; i++ {
			res[i] = i
		}
		return res
	}
	m := len(pat)
	lps := make([]int, m)
	for i, length := 1, 0; i < m; {
		if pat[i] == pat[length] {
			length++
			lps[i] = length
			i++
		} else if length > 0 {
			length = lps[length-1]
		} else {
			i++
		}
	}
	res := make([]int, 0)
	for i, j := 0, 0; i < n; {
		if s[i] == pat[j] {
			i++
			j++
			if j == m {
				res = append(res, i-m)
				j = lps[j-1]
			}
		} else if j > 0 {
			j = lps[j-1]
		} else {
			i++
		}
	}
	return res
}
