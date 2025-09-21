func rearrangeString(s string, k int) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	pq := priorityqueue.NewWith(func(a, b any) int {
		x := a.([2]int)
		y := b.([2]int)
		return y[0] - x[0]
	})

	for i := 0; i < 26; i++ {
		if cnt[i] > 0 {
			pq.Enqueue([2]int{cnt[i], i})
		}
	}

	var q [][2]int
	var ans strings.Builder

	for pq.Size() > 0 {
		p, _ := pq.Dequeue()
		pair := p.([2]int)
		pair[0]--
		ans.WriteByte(byte('a' + pair[1]))
		q = append(q, pair)

		if len(q) >= k {
			front := q[0]
			q = q[1:]
			if front[0] > 0 {
				pq.Enqueue(front)
			}
		}
	}

	if ans.Len() < len(s) {
		return ""
	}
	return ans.String()
}
