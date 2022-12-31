func equalCountSubstrings(s string, count int) (ans int) {
	n := len(s)
	for x := 1; x < 27 && x*count <= n; x++ {
		m := x * count
		y := 0
		cnt := [26]int{}
		for i, c := range s {
			a := c - 'a'
			cnt[a]++
			if cnt[a] == count {
				y++
			}
			if cnt[a] == count+1 {
				y--
			}
			j := i - m
			if j >= 0 {
				b := s[j] - 'a'
				cnt[b]--
				if cnt[b] == count {
					y++
				}
				if cnt[b] == count-1 {
					y--
				}
			}
			if x == y {
				ans++
			}
		}
	}
	return
}