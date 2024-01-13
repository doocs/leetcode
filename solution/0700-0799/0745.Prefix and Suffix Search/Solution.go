type WordFilter struct {
	d map[string]int
}

func Constructor(words []string) WordFilter {
	d := map[string]int{}
	for k, w := range words {
		n := len(w)
		for i := 0; i <= n; i++ {
			a := w[:i]
			for j := 0; j <= n; j++ {
				b := w[j:]
				d[a+"."+b] = k
			}
		}
	}
	return WordFilter{d}
}

func (this *WordFilter) F(pref string, suff string) int {
	if v, ok := this.d[pref+"."+suff]; ok {
		return v
	}
	return -1
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * obj := Constructor(words);
 * param_1 := obj.F(pref,suff);
 */