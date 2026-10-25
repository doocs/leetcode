func maximizeSum(nums []int, k int) int64 {
	f := func(x int) int {
		cnt := [10]int{}
		for x > 0 {
			v := x % 10
			x /= 10
			cnt[v]++
		}
		y := 0
		for i := 9; i >= 0; i-- {
			for j := 0; j < cnt[i]; j++ {
				y = y*10 + i
			}
		}
		return y
	}
	n := len(nums)
	t := make([]int, n)
	ids := make([]int, n)
	for i, x := range nums {
		t[i] = f(x)
		ids[i] = i
	}
	sort.Slice(ids, func(i, j int) bool {
		return t[ids[i]]-nums[ids[i]] > t[ids[j]]-nums[ids[j]]
	})
	var ans int64
	for i := 0; i < n; i++ {
		x := nums[ids[i]]
		if i < k {
			ans += int64(t[ids[i]])
		} else {
			ans += int64(x)
		}
	}
	return ans
}
