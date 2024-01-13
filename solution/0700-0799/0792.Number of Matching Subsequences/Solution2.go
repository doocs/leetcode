func numMatchingSubseq(s string, words []string) (ans int) {
	type pair struct{ i, j int }
	d := [26][]pair{}
	for i, w := range words {
		d[w[0]-'a'] = append(d[w[0]-'a'], pair{i, 0})
	}
	for _, c := range s {
		q := d[c-'a']
		d[c-'a'] = nil
		for _, p := range q {
			i, j := p.i, p.j+1
			if j == len(words[i]) {
				ans++
			} else {
				d[words[i][j]-'a'] = append(d[words[i][j]-'a'], pair{i, j})
			}
		}
	}
	return
}