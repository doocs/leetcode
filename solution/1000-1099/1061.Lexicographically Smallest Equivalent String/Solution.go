var p []int

func smallestEquivalentString(s1 string, s2 string, baseStr string) string {
	p = make([]int, 26)
	for i := 0; i < 26; i++ {
		p[i] = i
	}
	for i := 0; i < len(s1); i++ {
		a, b := int(s1[i]-'a'), int(s2[i]-'a')
		pa, pb := find(a), find(b)
		if pa < pb {
			p[pb] = pa
		} else {
			p[pa] = pb
		}
	}
	var res []byte
	for _, a := range baseStr {
		b := byte(find(int(a-'a'))) + 'a'
		res = append(res, b)
	}
	return string(res)
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}