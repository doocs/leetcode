func maxNumOfSubstrings(s string) (ans []string) {
	first := [26]int{}
	last := [26]int{}
	for i := range first {
		first[i] = -1
	}
	for i := range s {
		x := int(s[i] - 'a')
		if first[x] == -1 {
			first[x] = i
		}
		last[x] = i
	}
	var segs [][2]int
	for x, l := range first {
		if l == -1 {
			continue
		}
		r := last[x]
		i := l
		for ; i <= r; i++ {
			y := int(s[i] - 'a')
			if first[y] < l {
				break
			}
			r = max(r, last[y])
		}
		if i > r {
			segs = append(segs, [2]int{l, r})
		}
	}
	sort.Slice(segs, func(i, j int) bool { return segs[i][1] < segs[j][1] })
	end := -1
	for _, e := range segs {
		l, r := e[0], e[1]
		if l > end {
			ans = append(ans, s[l:r+1])
			end = r
		}
	}
	return
}
