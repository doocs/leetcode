func countExcellentPairs(nums []int, k int) int64 {
	s := map[int]bool{}
	for _, v := range nums {
		s[v] = true
	}
	cnt := make([]int, 32)
	for v := range s {
		t := bits.OnesCount(uint(v))
		cnt[t]++
	}
	ans := 0
	for v := range s {
		t := bits.OnesCount(uint(v))
		for i, x := range cnt {
			if t+i >= k {
				ans += x
			}
		}
	}
	return int64(ans)
}