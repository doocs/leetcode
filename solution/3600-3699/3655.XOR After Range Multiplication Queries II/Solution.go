func xorAfterQueries(nums []int, queries [][]int) int {
	const mod = 1_000_000_007
	n := len(nums)
	B := int(math.Sqrt(float64(n))) + 1
	events := make([][][][2]int, B+1)
	for k := 1; k <= B; k++ {
		events[k] = make([][][2]int, k)
	}
	qpow := func(a, e int) int {
		res := 1
		a %= mod
		for ; e > 0; e >>= 1 {
			if e&1 == 1 {
				res = res * a % mod
			}
			a = a * a % mod
		}
		return res
	}
	for _, q := range queries {
		l, r, k, v := q[0], q[1], q[2], q[3]
		if k > B {
			for idx := l; idx <= r; idx += k {
				nums[idx] = nums[idx] * v % mod
			}
		} else {
			res := l % k
			t1 := (l - res) / k
			t2 := (r - res) / k
			events[k][res] = append(events[k][res], [2]int{t1, v})
			if t2+1 <= (n-1-res)/k {
				events[k][res] = append(events[k][res], [2]int{t2 + 1, qpow(v, mod-2)})
			}
		}
	}
	for k := 1; k <= B; k++ {
		for res := 0; res < k; res++ {
			ev := events[k][res]
			if len(ev) == 0 {
				continue
			}
			sort.Slice(ev, func(i, j int) bool { return ev[i][0] < ev[j][0] })
			comp := make([][2]int, 0, len(ev))
			for _, p := range ev {
				if len(comp) > 0 && comp[len(comp)-1][0] == p[0] {
					comp[len(comp)-1][1] = comp[len(comp)-1][1] * p[1] % mod
				} else {
					comp = append(comp, p)
				}
			}
			cur, ptr, t := 1, 0, 0
			for idx := res; idx < n; idx, t = idx+k, t+1 {
				for ptr < len(comp) && comp[ptr][0] == t {
					cur = cur * comp[ptr][1] % mod
					ptr++
				}
				nums[idx] = nums[idx] * cur % mod
			}
		}
	}
	xr := 0
	for _, x := range nums {
		xr ^= x
	}
	return xr
}
