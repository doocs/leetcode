func countSubarrays(nums []int, k int, m int) int64 {
	f := func(lim int) int64 {
		cnt := make(map[int]int)
		var ans int64
		l := 0
		t := 0

		for _, x := range nums {
			cnt[x]++
			if cnt[x] == m {
				t++
			}

			for len(cnt) >= lim && t >= k {
				y := nums[l]
				l++
				cnt[y]--
				if cnt[y] == m-1 {
					t--
				}
				if cnt[y] == 0 {
					delete(cnt, y)
				}
			}

			ans += int64(l)
		}

		return ans
	}

	return f(k) - f(k+1)
}
