func maximumANDSum(nums []int, numSlots int) (ans int) {
	n := len(nums)
	m := numSlots << 1
	f := make([]int, 1<<m)
	for i := range f {
		cnt := bits.OnesCount(uint(i))
		if cnt > n {
			continue
		}
		for j := 0; j < m; j++ {
			if i>>j&1 == 1 {
				f[i] = max(f[i], f[i^(1<<j)]+(nums[cnt-1]&(j/2+1)))
			}
		}
		ans = max(ans, f[i])
	}
	return
}