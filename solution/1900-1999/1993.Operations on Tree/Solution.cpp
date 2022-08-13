class LockingTree {
public:
    unordered_map<int, int> nums;
    vector<int> parent;
    vector<vector<int>> children;

    LockingTree(vector<int>& parent) {
        this->parent = parent;
        int n = parent.size();
        children.resize(n);
        for (int i = 0; i < n; ++i)
            if (parent[i] != -1)
                children[parent[i]].push_back(i);
    }

    bool lock(int num, int user) {
        if (nums.count(num)) return false;
        nums[num] = user;
        return true;
    }

    bool unlock(int num, int user) {
        if (!nums.count(num) || nums[num] != user) return false;
        nums.erase(num);
        return true;
    }

    bool upgrade(int num, int user) {
        for (int t = num; t != -1; t = parent[t])
            if (nums.count(t))
                return false;
        bool find = false;
        dfs(num, find);
        if (!find) return false;
        nums[num] = user;
        return true;
    }

    void dfs(int num, bool& find) {
        for (int child : children[num]) {
            if (nums.count(child)) {
                nums.erase(child);
                find = true;
            }
            dfs(child, find);
        }
    }
};

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree* obj = new LockingTree(parent);
 * bool param_1 = obj->lock(num,user);
 * bool param_2 = obj->unlock(num,user);
 * bool param_3 = obj->upgrade(num,user);
 */