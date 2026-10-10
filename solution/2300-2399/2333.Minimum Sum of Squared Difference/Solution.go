func minSumSquareDiff(nums1 []int, nums2 []int, k1 int, k2 int) int64 {
	k := k1 + k2
	var s int64
	mx := 0
	cnt := make([]int, 100001)
	for i, a := range nums1 {
		v := abs(a - nums2[i])
		cnt[v]++
		s += int64(v)
		mx = max(mx, v)
	}
	if s <= int64(k) {
		return 0
	}
	for v := mx; v > 0 && k > 0; v-- {
		if cnt[v] == 0 {
			continue
		}
		take := min(cnt[v], k)
		k -= take
		cnt[v] -= take
		cnt[v-1] += take
	}
	var ans int64
	for v := 0; v <= mx; v++ {
		ans += int64(v) * int64(v) * int64(cnt[v])
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
