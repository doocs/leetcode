package CountingSort

func CountingSort(nums []int, min, max int) {
	n := len(nums)
	k := max - min + 1
	c := make([]int, k)
	for _, v := range nums {
		c[v-min]++
	}

	for i := 1; i < k; i++ {
		c[i] += c[i-1]
	}

	r := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		a := c[v]
		r[a-1] = v + min
		c[v]--
	}

	for i, v := range r {
		nums[i] = v
	}
}
