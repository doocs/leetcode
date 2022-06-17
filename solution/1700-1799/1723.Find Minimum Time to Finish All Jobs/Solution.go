func minimumTimeRequired(jobs []int, k int) int {
	cnt := make([]int, k)
	ans := 0x3f3f3f3f
	sort.Slice(jobs, func(i, j int) bool {
		return jobs[i] > jobs[j]
	})
	var dfs func(int)
	dfs = func(i int) {
		if i == len(jobs) {
			mx := 0
			for _, v := range cnt {
				mx = max(mx, v)
			}
			ans = min(ans, mx)
			return
		}
		for j := 0; j < k; j++ {
			if cnt[j]+jobs[i] >= ans {
				continue
			}
			cnt[j] += jobs[i]
			dfs(i + 1)
			cnt[j] -= jobs[i]
			if cnt[j] == 0 {
				break
			}
		}
	}
	dfs(0)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}