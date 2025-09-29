func majorityFrequencyGroup(s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}

	f := make(map[int][]byte)
	for i, v := range cnt {
		if v > 0 {
			f[v] = append(f[v], byte('a'+i))
		}
	}

	mx, mv := 0, 0
	var ans []byte
	for v, cs := range f {
		if len(cs) > mx || (len(cs) == mx && v > mv) {
			mx = len(cs)
			mv = v
			ans = cs
		}
	}
	return string(ans)
}
