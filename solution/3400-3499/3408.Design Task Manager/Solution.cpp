class TaskManager {
private:
    unordered_map<int, pair<int, int>> d;
    set<pair<int, int>> st;

public:
    TaskManager(vector<vector<int>>& tasks) {
        for (const auto& task : tasks) {
            add(task[0], task[1], task[2]);
        }
    }

    void add(int userId, int taskId, int priority) {
        d[taskId] = {userId, priority};
        st.insert({-priority, -taskId});
    }

    void edit(int taskId, int newPriority) {
        auto [userId, priority] = d[taskId];
        st.erase({-priority, -taskId});
        st.insert({-newPriority, -taskId});
        d[taskId] = {userId, newPriority};
    }

    void rmv(int taskId) {
        auto [userId, priority] = d[taskId];
        st.erase({-priority, -taskId});
        d.erase(taskId);
    }

    int execTop() {
        if (st.empty()) {
            return -1;
        }
        auto e = *st.begin();
        st.erase(st.begin());
        int taskId = -e.second;
        int userId = d[taskId].first;
        d.erase(taskId);
        return userId;
    }
};

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager* obj = new TaskManager(tasks);
 * obj->add(userId,taskId,priority);
 * obj->edit(taskId,newPriority);
 * obj->rmv(taskId);
 * int param_4 = obj->execTop();
 */
