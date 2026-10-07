func maxPartitionsAfterOperations(s string, k int) int {
	n := len(s)
	masks := make([]int, n)
	for i := 0; i < n; i++ {
		masks[i] = 1 << (s[i] - 'a')
	}
	reach := make([]map[int]struct{}, n+1)
	f := make([]map[int]int, n+1)
	for i := 0; i <= n; i++ {
		reach[i] = map[int]struct{}{}
		f[i] = map[int]int{}
	}
	reach[0][1] = struct{}{}
	for i, v := range masks {
		for key := range reach[i] {
			cur, t := key>>1, key&1
			nxt := cur | v
			if bits.OnesCount(uint(nxt)) > k {
				reach[i+1][(v<<1)|t] = struct{}{}
			} else {
				reach[i+1][(nxt<<1)|t] = struct{}{}
			}
			if t == 1 {
				for j := 0; j < 26; j++ {
					bit := 1 << j
					nxt = cur | bit
					if bits.OnesCount(uint(nxt)) > k {
						reach[i+1][bit<<1] = struct{}{}
					} else {
						reach[i+1][nxt<<1] = struct{}{}
					}
				}
			}
		}
	}
	get := func(i, key int) int {
		if i == n {
			return 1
		}
		return f[i][key]
	}
	for i := n - 1; i >= 0; i-- {
		v := masks[i]
		for key := range reach[i] {
			cur, t := key>>1, key&1
			nxt := cur | v
			var ans int
			if bits.OnesCount(uint(nxt)) > k {
				ans = get(i+1, (v<<1)|t) + 1
			} else {
				ans = get(i+1, (nxt<<1)|t)
			}
			if t == 1 {
				for j := 0; j < 26; j++ {
					bit := 1 << j
					nxt = cur | bit
					if bits.OnesCount(uint(nxt)) > k {
						ans = max(ans, get(i+1, bit<<1)+1)
					} else {
						ans = max(ans, get(i+1, nxt<<1))
					}
				}
			}
			f[i][key] = ans
		}
	}
	return f[0][1]
}
