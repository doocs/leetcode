func minSplitMerge(nums1 []int, nums2 []int) int {
	n := len(nums1)

	toArr := func(nums []int) [6]int {
		var t [6]int
		for i, x := range nums {
			t[i] = x
		}
		return t
	}

	start := toArr(nums1)
	target := toArr(nums2)

	vis := map[[6]int]bool{start: true}
	q := [][6]int{start}

	for ans := 0; ; ans++ {
		nq := [][6]int{}
		for _, cur := range q {
			if cur == target {
				return ans
			}
			for l := 0; l < n; l++ {
				for r := l; r < n; r++ {
					remain := []int{}
					for i := 0; i < l; i++ {
						remain = append(remain, cur[i])
					}
					for i := r + 1; i < n; i++ {
						remain = append(remain, cur[i])
					}

					sub := []int{}
					for i := l; i <= r; i++ {
						sub = append(sub, cur[i])
					}

					for pos := 0; pos <= len(remain); pos++ {
						nxtSlice := []int{}
						nxtSlice = append(nxtSlice, remain[:pos]...)
						nxtSlice = append(nxtSlice, sub...)
						nxtSlice = append(nxtSlice, remain[pos:]...)

						nxt := toArr(nxtSlice)
						if !vis[nxt] {
							vis[nxt] = true
							nq = append(nq, nxt)
						}
					}
				}
			}
		}
		q = nq
	}
}
