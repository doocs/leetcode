func halveArray(nums []int) (ans int) {
	half := 0
	for i := range nums {
		nums[i] <<= 20
		half += nums[i]
	}
	h := hp{nums}
	heap.Init(&h)
	for half >>= 1; half > 0; ans++ {
		half -= h.IntSlice[0] >> 1
		h.IntSlice[0] >>= 1
		heap.Fix(&h, 0)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Push(interface{})     {}
func (hp) Pop() (_ interface{}) { return }
