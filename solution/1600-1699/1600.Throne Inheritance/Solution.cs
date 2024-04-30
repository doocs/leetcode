public class ThroneInheritance {
    private string king;
    private HashSet<string> dead = new HashSet<string>();
    private Dictionary<string, List<string>> g = new Dictionary<string, List<string>>();
    private List<string> ans = new List<string>();

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
        ans.Clear();
        DFS(king);
        return ans;
    }

    private void DFS(string x) {
        if (!dead.Contains(x)) {
            ans.Add(x);
        }
        if (g.ContainsKey(x)) {
            foreach (string y in g[x]) {
                DFS(y);
            }
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.Birth(parentName,childName);
 * obj.Death(name);
 * IList<string> param_3 = obj.GetInheritanceOrder();
 */