func gcdValues(nums []int, queries []int64) (ans []int) {
	mx := slices.Max(nums)
	cnt := make([]int, mx+1)
	cntG := make([]int, mx+1)
	for _, x := range nums {
		cnt[x]++
	}
	for i := mx; i > 0; i-- {
		var v int
		for j := i; j <= mx; j += i {
			v += cnt[j]
			cntG[i] -= cntG[j]
		}
		cntG[i] += v * (v - 1) / 2
	}
	for i := 2; i <= mx; i++ {
		cntG[i] += cntG[i-1]
	}
	for _, q := range queries {
		ans = append(ans, sort.SearchInts(cntG, int(q)+1))
	}
	return
}
