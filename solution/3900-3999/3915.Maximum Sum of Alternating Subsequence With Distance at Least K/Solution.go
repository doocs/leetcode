type fenwick []int64

func (f fenwick) update(i int, val int64) {
	for ; i < len(f); i += i & -i {
		f[i] = max(f[i], val)
	}
}

func (f fenwick) query(i int) (res int64) {
	for ; i > 0; i &= i - 1 {
		res = max(res, f[i])
	}
	return
}

func maxAlternatingSum(nums []int, k int) (ans int64) {
	sorted := slices.Clone(nums)
	slices.Sort(sorted)
	sorted = slices.Compact(sorted)
	m := len(sorted)
	bit0 := make(fenwick, m+1)
	bit1 := make(fenwick, m+1)
	n := len(nums)
	f := make([][2]int64, n)
	for i, x := range nums {
		if i >= k {
			r := sort.SearchInts(sorted, nums[i-k]) + 1
			bit0.update(r, f[i-k][0])
			bit1.update(m+1-r, f[i-k][1])
		}
		r := sort.SearchInts(sorted, x) + 1
		f[i][0] = int64(x) + bit1.query(m-r)
		f[i][1] = int64(x) + bit0.query(r-1)
		ans = max(ans, f[i][0], f[i][1])
	}
	return
}
