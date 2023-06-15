type RandomizedSet struct {
	d map[int]int
	q []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{map[int]int{}, []int{}}
}

func (this *RandomizedSet) Insert(val int) bool {
	if _, ok := this.d[val]; ok {
		return false
	}
	this.d[val] = len(this.q)
	this.q = append(this.q, val)
	return true
}

func (this *RandomizedSet) Remove(val int) bool {
	if _, ok := this.d[val]; !ok {
		return false
	}
	i := this.d[val]
	this.d[this.q[len(this.q)-1]] = i
	this.q[i] = this.q[len(this.q)-1]
	this.q = this.q[:len(this.q)-1]
	delete(this.d, val)
	return true
}

func (this *RandomizedSet) GetRandom() int {
	return this.q[rand.Intn(len(this.q))]
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Insert(val);
 * param_2 := obj.Remove(val);
 * param_3 := obj.GetRandom();
 */