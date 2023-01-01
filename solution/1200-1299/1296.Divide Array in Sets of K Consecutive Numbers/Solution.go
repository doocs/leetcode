func isPossibleDivide(nums []int, k int) bool {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	sort.Ints(nums)
	for _, v := range nums {
		if _, ok := cnt[v]; ok {
			for x := v; x < v+k; x++ {
				if _, ok := cnt[x]; !ok {
					return false
				}
				cnt[x]--
				if cnt[x] == 0 {
					delete(cnt, x)
				}
			}
		}
	}
	return true
}