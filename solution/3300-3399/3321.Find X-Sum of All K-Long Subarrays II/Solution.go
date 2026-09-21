func findXSum(nums []int, k int, x int) []int64 {
	type pair struct{ cnt, v int }
	cmpFn := func(a, b pair) int { return cmp.Or(a.cnt-b.cnt, a.v-b.v) }
	l := redblacktree.NewWith[pair, struct{}](cmpFn)
	r := redblacktree.NewWith[pair, struct{}](cmpFn)
	cnt := map[int]int{}
	var s int64

	add := func(v int) {
		if cnt[v] == 0 {
			return
		}
		p := pair{cnt[v], v}
		if !l.Empty() && cmpFn(l.Left().Key, p) < 0 {
			s += int64(p.cnt) * int64(p.v)
			l.Put(p, struct{}{})
		} else {
			r.Put(p, struct{}{})
		}
	}
	remove := func(v int) {
		if cnt[v] == 0 {
			return
		}
		p := pair{cnt[v], v}
		if _, ok := l.Get(p); ok {
			s -= int64(p.cnt) * int64(p.v)
			l.Remove(p)
		} else {
			r.Remove(p)
		}
	}

	n := len(nums)
	ans := make([]int64, 0, n-k+1)
	for i, v := range nums {
		remove(v)
		cnt[v]++
		add(v)
		j := i - k + 1
		if j < 0 {
			continue
		}
		for !r.Empty() && l.Size() < x {
			p := r.Right().Key
			s += int64(p.cnt) * int64(p.v)
			r.Remove(p)
			l.Put(p, struct{}{})
		}
		for l.Size() > x {
			p := l.Left().Key
			s -= int64(p.cnt) * int64(p.v)
			l.Remove(p)
			r.Put(p, struct{}{})
		}
		ans = append(ans, s)
		remove(nums[j])
		cnt[nums[j]]--
		add(nums[j])
	}
	return ans
}
