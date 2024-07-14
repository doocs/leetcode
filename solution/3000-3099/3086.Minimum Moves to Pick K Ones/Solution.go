func minimumMoves(nums []int, k int, maxChanges int) int64 {
	n := len(nums)
	cnt := make([]int, n+1)
	s := make([]int, n+1)

	for i := 1; i <= n; i++ {
		cnt[i] = cnt[i-1] + nums[i-1]
		s[i] = s[i-1] + i*nums[i-1]
	}

	ans := math.MaxInt64

	for i := 1; i <= n; i++ {
		t := 0
		need := k - nums[i-1]

		for _, j := range []int{i - 1, i + 1} {
			if need > 0 && 1 <= j && j <= n && nums[j-1] == 1 {
				need--
				t++
			}
		}

		c := min(need, maxChanges)
		need -= c
		t += c * 2

		if need <= 0 {
			ans = min(ans, t)
			continue
		}

		l, r := 2, max(i-1, n-i)

		for l <= r {
			mid := (l + r) >> 1
			l1, r1 := max(1, i-mid), max(0, i-2)
			l2, r2 := min(n+1, i+2), min(n, i+mid)

			c1 := cnt[r1] - cnt[l1-1]
			c2 := cnt[r2] - cnt[l2-1]

			if c1+c2 >= need {
				t1 := c1*i - (s[r1] - s[l1-1])
				t2 := s[r2] - s[l2-1] - c2*i
				ans = min(ans, t+t1+t2)
				r = mid - 1
			} else {
				l = mid + 1
			}
		}
	}

	return int64(ans)
}