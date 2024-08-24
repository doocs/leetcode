func medianOfUniquenessArray(nums []int) int {
	n := len(nums)
	m := (1 + n) * n / 2
	return sort.Search(n, func(mx int) bool {
		cnt := map[int]int{}
		l, k := 0, 0
		for r, x := range nums {
			cnt[x]++
			for len(cnt) > mx {
				y := nums[l]
				cnt[y]--
				if cnt[y] == 0 {
					delete(cnt, y)
				}
				l++
			}
			k += r - l + 1
			if k >= (m+1)/2 {
				return true
			}
		}
		return false
	})
}
