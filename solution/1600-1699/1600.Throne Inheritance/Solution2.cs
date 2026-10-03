public class ThroneInheritance {
    private string king;
    private HashSet<string> dead = new HashSet<string>();
    private Dictionary<string, List<string>> g = new Dictionary<string, List<string>>();

    public ThroneInheritance(string kingName) {
        king = kingName;
    }

    public void Birth(string parentName, string childName) {
        if (!g.ContainsKey(parentName)) {
            g[parentName] = new List<string>();
        }
        g[parentName].Add(childName);
    }

    public void Death(string name) {
        dead.Add(name);
    }

    public IList<string> GetInheritanceOrder() {
        List<string> ans = new List<string>();
        Stack<string> stk = new Stack<string>();
        stk.Push(king);
        while (stk.Count > 0) {
            string x = stk.Pop();
            if (!dead.Contains(x)) {
                ans.Add(x);
            }
            if (g.ContainsKey(x)) {
                List<string> children = g[x];
                for (int i = children.Count - 1; i >= 0; --i) {
                    stk.Push(children[i]);
                }
            }
        }
        return ans;
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.Birth(parentName,childName);
 * obj.Death(name);
 * IList<string> param_3 = obj.GetInheritanceOrder();
 */
