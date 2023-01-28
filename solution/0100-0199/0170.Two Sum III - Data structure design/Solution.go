type TwoSum struct {
	cnt map[int]int
}

func Constructor() TwoSum {
	return TwoSum{map[int]int{}}
}

func (this *TwoSum) Add(number int) {
	this.cnt[number]++
}

func (this *TwoSum) Find(value int) bool {
	for x, v := range this.cnt {
		y := value - x
		if _, ok := this.cnt[y]; ok && (x != y || v > 1) {
			return true
		}
	}
	return false
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Add(number);
 * param_2 := obj.Find(value);
 */