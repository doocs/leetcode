class ThroneInheritance {
public:
    ThroneInheritance(string kingName) {
        king = kingName;
    }

    void birth(string parentName, string childName) {
        g[parentName].emplace_back(childName);
    }

    void death(string name) {
        dead.insert(name);
    }

    vector<string> getInheritanceOrder() {
        ans.resize(0);
        dfs(king);
        return ans;
    }

private:
    string king;
    unordered_set<string> dead;
    unordered_map<string, vector<string>> g;
    vector<string> ans;

    void dfs(string& x) {
        if (!dead.contains(x)) {
            ans.emplace_back(x);
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