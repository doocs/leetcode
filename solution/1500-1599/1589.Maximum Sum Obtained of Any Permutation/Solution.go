func maxSumRangeQuery(nums []int, requests [][]int) (ans int) {
	n := len(nums)
	d := make([]int, n)
	for _, req := range requests {
		l, r := req[0], req[1]
		d[l]++
		if r+1 < n {
			d[r+1]--
		}
	}
	for i := 1; i < n; i++ {
		d[i] += d[i-1]
	}
	sort.Ints(nums)
	sort.Ints(d)
	const mod = 1e9 + 7
	for i, a := range nums {
		b := d[i]
		ans = (ans + a*b) % mod
	}
	return
}