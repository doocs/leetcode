func minimumOperations(nums []int) int {
	f := func(i int) [4]int {
		cnt := make(map[int]int)
		for ; i < len(nums); i += 2 {
			cnt[nums[i]]++
		}

		k1, k2 := 0, 0
		for k, v := range cnt {
			if cnt[k1] < v {
				k2, k1 = k1, k
			} else if cnt[k2] < v {
				k2 = k
			}
		}
		return [4]int{k1, cnt[k1], k2, cnt[k2]}
	}

	a := f(0)
	b := f(1)
	n := len(nums)
	if a[0] != b[0] {
		return n - (a[1] + b[1])
	}
	return n - max(a[1]+b[3], a[3]+b[1])
}