type MapSum struct {
	data map[string]int
	t    map[string]int
}

/** Initialize your data structure here. */
func Constructor() MapSum {
	return MapSum{
		data: make(map[string]int),
		t:    make(map[string]int),
	}
}

func (this *MapSum) Insert(key string, val int) {
	old := this.t[key]
	this.t[key] = val
	for i := 1; i < len(key)+1; i++ {
		k := key[:i]
		this.data[k] += (val - old)
	}
}

func (this *MapSum) Sum(prefix string) int {
	return this.data[prefix]
}

/**
 * Your MapSum object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Insert(key,val);
 * param_2 := obj.Sum(prefix);
 */