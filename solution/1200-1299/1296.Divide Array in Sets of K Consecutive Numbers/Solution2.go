func isPossibleDivide(nums []int, k int) bool {
	if len(nums)%k != 0 {
		return false
	}
	m := treemap.NewWithIntComparator()
	for _, h := range nums {
		if v, ok := m.Get(h); ok {
			m.Put(h, v.(int)+1)
		} else {
			m.Put(h, 1)
		}
	}
	for !m.Empty() {
		v, _ := m.Min()
		for i := v.(int); i < v.(int)+k; i++ {
			if _, ok := m.Get(i); !ok {
				return false
			}
			if v, _ := m.Get(i); v.(int) == 1 {
				m.Remove(i)
			} else {
				m.Put(i, v.(int)-1)
			}
		}
	}
	return true
}