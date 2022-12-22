func longestSubstring(s string, k int) int {
	var dfs func(l, r int) int
	dfs = func(l, r int) int {
		cnt := [26]int{}
		for i := l; i <= r; i++ {
			cnt[s[i]-'a']++
		}
		var split byte
		for i, v := range cnt {
			if v > 0 && v < k {
				split = byte(i + 'a')
				break
			}
		}
		if split == 0 {
			return r - l + 1
		}
		i := l
		ans := 0
		for i <= r {
			for i <= r && s[i] == split {
				i++
			}
			if i > r {
				break
			}
			j := i
			for j <= r && s[j] != split {
				j++
			}
			t := dfs(i, j-1)
			ans = max(ans, t)
			i = j
		}
		return ans
	}
	return dfs(0, len(s)-1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}