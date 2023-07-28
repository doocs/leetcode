func continuousSubarrays(nums []int) (ans int64) {
	i := 0
	tm := treemap.NewWithIntComparator()
	for j, x := range nums {
		if v, ok := tm.Get(x); ok {
			tm.Put(x, v.(int)+1)
		} else {
			tm.Put(x, 1)
		}
		for {
			a, _ := tm.Min()
			b, _ := tm.Max()
			if b.(int)-a.(int) > 2 {
				if v, _ := tm.Get(nums[i]); v.(int) == 1 {
					tm.Remove(nums[i])
				} else {
					tm.Put(nums[i], v.(int)-1)
				}
				i++
			} else {
				break
			}
		}
		ans += int64(j - i + 1)
	}
	return
}