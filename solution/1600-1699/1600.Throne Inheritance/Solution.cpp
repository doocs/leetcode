class ThroneInheritance {
public:
    unordered_map<string, vector<string>> g;
    unordered_set<string> dead;
    string king;
    vector<string> ans;

    ThroneInheritance(string kingName) {
        king = kingName;
    }

    void birth(string parentName, string childName) {
        g[parentName].push_back(childName);
    }

    void death(string name) {
        dead.insert(name);
    }

    vector<string> getInheritanceOrder() {
        ans.resize(0);
        dfs(king);
        return ans;
    }

    void dfs(string& x) {
        if (!dead.count(x)) {
            ans.push_back(x);
        }
        for (auto& y : g[x]) {
            dfs(y);
        }
    }
};

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance* obj = new ThroneInheritance(kingName);
 * obj->birth(parentName,childName);
 * obj->death(name);
 * vector<string> param_3 = obj->getInheritanceOrder();
 */