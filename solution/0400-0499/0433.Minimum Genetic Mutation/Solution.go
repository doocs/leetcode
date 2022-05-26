func minMutation(start string, end string, bank []string) int {
	s := make(map[string]bool)
	for _, b := range bank {
		s[b] = true
	}
	mp := make(map[byte]string)
	mp['A'] = "TCG"
	mp['T'] = "ACG"
	mp['C'] = "ATG"
	mp['G'] = "ATC"
	type pair struct {
		first  string
		second int
	}
	q := []pair{{start, 0}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		t, step := p.first, p.second
		if t == end {
			return step
		}
		for i := 0; i < len(t); i++ {
			for _, c := range mp[t[i]] {
				next := t[:i] + string(c) + t[i+1:]
				if s[next] {
					q = append(q, pair{next, step + 1})
					s[next] = false
				}
			}
		}
	}
	return -1
}