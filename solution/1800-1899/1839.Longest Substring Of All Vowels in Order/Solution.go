func longestBeautifulSubstring(word string) (ans int) {
	arr := []pair{}
	n := len(word)
	for i := 0; i < n; {
		j := i
		for j < n && word[j] == word[i] {
			j++
		}
		arr = append(arr, pair{word[i], j - i})
		i = j
	}
	for i := 0; i < len(arr)-4; i++ {
		a, b, c, d, e := arr[i], arr[i+1], arr[i+2], arr[i+3], arr[i+4]
		if a.c == 'a' && b.c == 'e' && c.c == 'i' && d.c == 'o' && e.c == 'u' {
			ans = max(ans, a.v+b.v+c.v+d.v+e.v)
		}
	}
	return
}

type pair struct {
	c byte
	v int
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}