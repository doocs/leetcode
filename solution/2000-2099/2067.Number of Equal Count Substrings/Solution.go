func equalCountSubstrings(s string, count int) (ans int) {
	n := len(s)
	for i := 1; i < 27 && i*count <= n; i++ {
		k := i * count
		cnt := [26]int{}
		t := 0
		for j, c := range s {
			a := c - 'a'
			cnt[a]++
			if cnt[a] == count {
				t++
			} else if cnt[a] == count+1 {
				t--
			}
			if j >= k {
				b := s[j-k] - 'a'
				cnt[b]--
				if cnt[b] == count {
					t++
				} else if cnt[b] == count-1 {
					t--
				}
			}
			if i == t {
				ans++
			}
		}
	}
	return
}