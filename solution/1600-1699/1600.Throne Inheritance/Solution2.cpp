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
        vector<string> ans;
        vector<string> stk{king};
        while (!stk.empty()) {
            string x = stk.back();
            stk.pop_back();
            if (!dead.contains(x)) {
                ans.emplace_back(x);
            }
            auto it = g.find(x);
            if (it == g.end()) {
                continue;
            }
            auto& children = it->second;
            for (int i = (int) children.size() - 1; i >= 0; --i) {
                stk.push_back(children[i]);
            }
        }
        return ans;
    }

private:
    string king;
    unordered_set<string> dead;
    unordered_map<string, vector<string>> g;
};

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance* obj = new ThroneInheritance(kingName);
 * obj->birth(parentName,childName);
 * obj->death(name);
 * vector<string> param_3 = obj->getInheritanceOrder();
 */
