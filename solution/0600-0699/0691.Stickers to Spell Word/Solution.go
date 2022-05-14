func minStickers(stickers []string, target string) int {
	q := []int{0}
	n := len(target)
	vis := make([]bool, 1<<n)
	vis[0] = true
	ans := 0
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			state := q[0]
			if state == (1<<n)-1 {
				return ans
			}
			q = q[1:]
			for _, s := range stickers {
				nxt := state
				cnt := make([]int, 26)
				for _, c := range s {
					cnt[c-'a']++
				}
				for i, c := range target {
					idx := c - 'a'
					if (nxt&(1<<i)) == 0 && cnt[idx] > 0 {
						nxt |= 1 << i
						cnt[idx]--
					}
				}
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
		ans++
	}
	return -1
}