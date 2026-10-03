type ThroneInheritance struct {
	king string
	dead map[string]bool
	g    map[string][]string
}

func Constructor(kingName string) ThroneInheritance {
	return ThroneInheritance{kingName, map[string]bool{}, map[string][]string{}}
}

func (this *ThroneInheritance) Birth(parentName string, childName string) {
	this.g[parentName] = append(this.g[parentName], childName)
}

func (this *ThroneInheritance) Death(name string) {
	this.dead[name] = true
}

func (this *ThroneInheritance) GetInheritanceOrder() (ans []string) {
	stk := []string{this.king}
	for len(stk) > 0 {
		x := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if !this.dead[x] {
			ans = append(ans, x)
		}
		children := this.g[x]
		for i := len(children) - 1; i >= 0; i-- {
			stk = append(stk, children[i])
		}
	}
	return
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * obj := Constructor(kingName);
 * obj.Birth(parentName,childName);
 * obj.Death(name);
 * param_3 := obj.GetInheritanceOrder();
 */
