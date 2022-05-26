type RandomizedSet struct {
	m map[int]int
	l []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{map[int]int{}, []int{}}
}

func (this *RandomizedSet) Insert(val int) bool {
	if _, ok := this.m[val]; ok {
		return false
	}
	this.m[val] = len(this.l)
	this.l = append(this.l, val)
	return true
}

func (this *RandomizedSet) Remove(val int) bool {
	if _, ok := this.m[val]; !ok {
		return false
	}
	idx := this.m[val]
	this.l[idx] = this.l[len(this.l)-1]
	this.m[this.l[len(this.l)-1]] = idx
	this.l = this.l[:len(this.l)-1]
	delete(this.m, val)
	return true
}

func (this *RandomizedSet) GetRandom() int {
	return this.l[rand.Intn(len(this.l))]
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */