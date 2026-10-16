func sumCounts(nums []int) int {
	const MOD = 1000000007
	n := len(nums)
	size := 4 * n
	sum := make([]int64, size)
	sumSq := make([]int64, size)
	lazy := make([]int64, size)   
	var apply func(node, l, r int, v int64)
	apply = func(node, l, r int, v int64) {
		length := int64(r - l + 1)
		sumSq[node] = (sumSq[node] + 2*v*sum[node] + v*v%MOD*length) % MOD
		sum[node] = (sum[node] + v*length) % MOD
		lazy[node] = (lazy[node] + v) % MOD
	}
	var pushDown func(node, l, r int)
	pushDown = func(node, l, r int) {
		if lazy[node] != 0 && l != r {
			mid := (l + r) >> 1
			v := lazy[node]
			apply(node<<1, l, mid, v)
			apply(node<<1|1, mid+1, r, v)
			lazy[node] = 0
		}
	}
	var update func(node, l, r, ql, qr int, v int64)
	update = func(node, l, r, ql, qr int, v int64) {
		if ql > r || qr < l {
			return
		}
		if ql <= l && r <= qr {
			apply(node, l, r, v)
			return
		}
		pushDown(node, l, r)
		mid := (l + r) >> 1
		update(node<<1, l, mid, ql, qr, v)
		update(node<<1|1, mid+1, r, ql, qr, v)
		sum[node] = (sum[node<<1] + sum[node<<1|1]) % MOD
		sumSq[node] = (sumSq[node<<1] + sumSq[node<<1|1]) % MOD
	}
	last := make(map[int]int)
	var ans int64 = 0
	for r := 0; r < n; r++ {
		x := nums[r]
		p := -1
		if v, ok := last[x]; ok {
			p = v
		}
		update(1, 0, n-1, p+1, r, 1)
		last[x] = r
		ans = (ans + sumSq[1]) % MOD
	}
	return int(ans)
}