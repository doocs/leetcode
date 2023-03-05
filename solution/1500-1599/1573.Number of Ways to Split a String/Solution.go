func numWays(s string) int {
	cnt := 0
	for _, c := range s {
		if c == '1' {
			cnt++
		}
	}
	m := cnt % 3
	if m != 0 {
		return 0
	}
	const mod = 1e9 + 7
	n := len(s)
	if cnt == 0 {
		return (n - 1) * (n - 2) / 2 % mod
	}
	cnt /= 3
	find := func(x int) int {
		t := 0
		for i := 0; ; i++ {
			if s[i] == '1' {
				t++
				if t == x {
					return i
				}
			}
		}
	}
	i1, i2 := find(cnt), find(cnt+1)
	j1, j2 := find(cnt*2), find(cnt*2+1)
	return (i2 - i1) * (j2 - j1) % mod
}