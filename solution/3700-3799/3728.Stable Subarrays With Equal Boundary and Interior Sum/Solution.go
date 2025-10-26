func countStableSubarrays(capacity []int) (ans int64) {
	n := len(capacity)
	s := make([]int64, n+1)
	for i := 1; i <= n; i++ {
		s[i] = s[i-1] + int64(capacity[i-1])
	}

	type key struct {
		first  int
		second int64
	}

	cnt := make(map[key]int)

	for r := 2; r < n; r++ {
		l := r - 2
		keyL := key{capacity[l], int64(capacity[l]) + s[l+1]}
		cnt[keyL] += 1

		keyR := key{capacity[r], s[r]}
		ans += int64(cnt[keyR])
	}

	return
}
