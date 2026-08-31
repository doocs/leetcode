func minMergeCost(lists [][]int) int64 {
	n := len(lists)
	set := map[int]struct{}{}
	for _, v := range lists {
		for _, x := range v {
			set[x] = struct{}{}
		}
	}
	vals := make([]int, 0, len(set))
	for x := range set {
		vals = append(vals, x)
	}
	sort.Ints(vals)

	cnt := make([]int, 1<<n)
	med := make([]int, 1<<n)
	for i := 1; i < 1<<n; i++ {
		for j, v := range lists {
			if i>>j&1 == 1 {
				cnt[i] += len(v)
			}
		}
		need := (cnt[i] + 1) / 2
		l, r := 0, len(vals)-1
		for l < r {
			mid := (l + r) >> 1
			le := 0
			for b := i; b > 0; b &= b - 1 {
				id := bits.TrailingZeros(uint(b))
				le += sort.Search(len(lists[id]), func(p int) bool { return lists[id][p] > vals[mid] })
				if le >= need {
					break
				}
			}
			if le >= need {
				r = mid
			} else {
				l = mid + 1
			}
		}
		med[i] = vals[l]
	}

	f := make([]int64, 1<<n)
	for i := range f {
		f[i] = 1e18
	}
	for i := 1; i < 1<<n; i++ {
		if bits.OnesCount(uint(i)) == 1 {
			f[i] = 0
			continue
		}
		for j := (i - 1) & i; j > 0; j = (j - 1) & i {
			k := i ^ j
			if j <= k {
				d := med[j] - med[k]
				if d < 0 {
					d = -d
				}
				f[i] = min(f[i], f[j]+f[k]+int64(d))
			}
		}
		f[i] += int64(cnt[i])
	}
	return f[1<<n-1]
}
