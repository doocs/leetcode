type ThroneInheritance struct {
	g    map[string][]string
	dead map[string]bool
	king string
}

func Constructor(kingName string) ThroneInheritance {
	g := map[string][]string{}
	dead := map[string]bool{}
	return ThroneInheritance{g, dead, kingName}
}

func (this *ThroneInheritance) Birth(parentName string, childName string) {
	this.g[parentName] = append(this.g[parentName], childName)
}

func (this *ThroneInheritance) Death(name string) {
	this.dead[name] = true
}

func (this *ThroneInheritance) GetInheritanceOrder() []string {
	var dfs func(x string)
	ans := []string{}

	dfs = func(x string) {
		if !this.dead[x] {
			ans = append(ans, x)
		}
		for _, y := range this.g[x] {
			dfs(y)
		}
	}
	dfs(this.king)
	return ans
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * obj := Constructor(kingName);
 * obj.Birth(parentName,childName);
 * obj.Death(name);
 * param_3 := obj.GetInheritanceOrder();
 */