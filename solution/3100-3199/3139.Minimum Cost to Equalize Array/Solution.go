func minCostToEqualizeArray(nums []int, cost1 int, cost2 int) int {
	mx, mod := 0, int(1e9)+7
	for _, v := range nums {
		if v > mx {
			mx = v
		}
	}
	mv, cnt := 0, 0
	for _, v := range nums {
		d := mx - v
		if cnt += d; d > mv {
			mv = d
		}
	}
	ans := find(mv, cnt, cost1, cost2)
	if len(nums) <= 2 {
		return ans % mod
	}
	for (mv << 1) > cnt {
		mv, cnt = mv+1, cnt+len(nums)
		ans = min(ans, find(mv, cnt, cost1, cost2))
	}
	mv, cnt = mv+1, cnt+len(nums)
	ans = min(ans, find(mv, cnt, cost1, cost2))
	return ans % mod
}

func find(mv, cnt, c1, c2 int) int {
	switch {
	case c1<<1 <= c2:
		return cnt * c1
	case mv<<1 <= cnt:
		return (cnt>>1)*c2 + (cnt&1)*c1
	}
	return (cnt-mv)*c2 + (mv<<1-cnt)*c1
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
