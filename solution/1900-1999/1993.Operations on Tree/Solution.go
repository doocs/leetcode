type LockingTree struct {
	nums     map[int]int
	parent   []int
	children [][]int
}

func Constructor(parent []int) LockingTree {
	n := len(parent)
	nums := make(map[int]int)
	children := make([][]int, n)
	for i, p := range parent {
		if p != -1 {
			children[p] = append(children[p], i)
		}
	}
	return LockingTree{nums, parent, children}
}

func (this *LockingTree) Lock(num int, user int) bool {
	if _, ok := this.nums[num]; ok {
		return false
	}
	this.nums[num] = user
	return true
}

func (this *LockingTree) Unlock(num int, user int) bool {
	if this.nums[num] != user {
		return false
	}
	delete(this.nums, num)
	return true
}

func (this *LockingTree) Upgrade(num int, user int) bool {
	for t := num; t != -1; t = this.parent[t] {
		if _, ok := this.nums[t]; ok {
			return false
		}
	}
	find := false
	var dfs func(int)
	dfs = func(num int) {
		for _, child := range this.children[num] {
			if _, ok := this.nums[child]; ok {
				delete(this.nums, child)
				find = true
			}
			dfs(child)
		}
	}
	dfs(num)
	if !find {
		return false
	}
	this.nums[num] = user
	return true
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * obj := Constructor(parent);
 * param_1 := obj.Lock(num,user);
 * param_2 := obj.Unlock(num,user);
 * param_3 := obj.Upgrade(num,user);
 */