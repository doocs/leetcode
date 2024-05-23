func minStickers(stickers []string, target string) (ans int) {
	n := len(target)
	q := []int{0}
	vis := make([]bool, 1<<n)
	vis[0] = true
	for ; len(q) > 0; ans++ {
		for m := len(q); m > 0; m-- {
			cur := q[0]
			q = q[1:]
			if cur == 1<<n-1 {
				return
			}
			for _, s := range stickers {
				cnt := [26]int{}
				for _, c := range s {
					cnt[c-'a']++
				}
				nxt := cur
				for i, c := range target {
					if cur>>i&1 == 0 && cnt[c-'a'] > 0 {
						nxt |= 1 << i
						cnt[c-'a']--
					}
				}
				if !vis[nxt] {
					vis[nxt] = true
					q = append(q, nxt)
				}
			}
		}
	}
	return -1
}