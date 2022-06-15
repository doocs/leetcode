func smallestDistancePair(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	left, right := 0, nums[n-1]-nums[0]
	count := func(dist int) int {
		cnt := 0
		for i, v := range nums {
			target := v - dist
			left, right := 0, i
			for left < right {
				mid := (left + right) >> 1
				if nums[mid] >= target {
					right = mid
				} else {
					left = mid + 1
				}
			}
			cnt += i - left
		}
		return cnt
	}
	for left < right {
		mid := (left + right) >> 1
		if count(mid) >= k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}