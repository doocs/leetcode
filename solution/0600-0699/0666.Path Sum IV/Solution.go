func pathSum(nums []int) int {
	ans := 0
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num/10] = num % 10
	}
	var dfs func(node, t int)
	dfs = func(node, t int) {
		if v, ok := mp[node]; ok {
			t += v
			d, p := node/10, node%10
			l := (d+1)*10 + (p * 2) - 1
			r := l + 1
			if _, ok1 := mp[l]; !ok1 {
				if _, ok2 := mp[r]; !ok2 {
					ans += t
					return
				}
			}
			dfs(l, t)
			dfs(r, t)
		}
	}
	dfs(11, 0)
	return ans
}