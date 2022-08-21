func shiftingLetters(s string, shifts [][]int) string {
	n := len(s)
	d := make([]int, n+1)
	for _, e := range shifts {
		if e[2] == 0 {
			e[2]--
		}
		d[e[0]] += e[2]
		d[e[1]+1] -= e[2]
	}
	for i := 1; i <= n; i++ {
		d[i] += d[i-1]
	}
	ans := []byte{}
	for i, c := range s {
		j := (int(c-'a') + d[i]%26 + 26) % 26
		ans = append(ans, byte('a'+j))
	}
	return string(ans)
}