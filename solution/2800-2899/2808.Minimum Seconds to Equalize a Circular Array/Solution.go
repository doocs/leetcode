func minimumSeconds(nums []int) int {
	d := map[int][]int{}
	for i, x := range nums {
		d[x] = append(d[x], i)
	}
	ans := 1 << 30
	n := len(nums)
	for _, idx := range d {
		m := len(idx)
		t := idx[0] + n - idx[m-1]
		for i := 1; i < m; i++ {
			t = max(t, idx[i]-idx[i-1])
		}
		ans = min(ans, t/2)
	}
	return ans
}