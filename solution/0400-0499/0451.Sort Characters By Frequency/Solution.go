type pair struct {
	b   byte
	cnt int
}

func frequencySort(s string) string {
	freq := make(map[byte]int)
	for _, r := range s {
		freq[byte(r)]++
	}
	a := make([]pair, 0)
	for k, v := range freq {
		a = append(a, pair{b: k, cnt: v})
	}
	sort.Slice(a, func(i, j int) bool { return a[i].cnt > a[j].cnt })
	var sb strings.Builder
	for _, p := range a {
		sb.Write(bytes.Repeat([]byte{p.b}, p.cnt))
	}
	return sb.String()
}
