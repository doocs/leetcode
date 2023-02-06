class LogSystem {
public:
    LogSystem() {
        d["Year"] = 4;
        d["Month"] = 7;
        d["Day"] = 10;
        d["Hour"] = 13;
        d["Minute"] = 16;
        d["Second"] = 19;
    }

    void put(int id, string timestamp) {
        logs.push_back({id, timestamp});
    }

    vector<int> retrieve(string start, string end, string granularity) {
        vector<int> ans;
        int i = d[granularity];
        auto s = start.substr(0, i);
        auto e = end.substr(0, i);
        for (auto& [id, ts] : logs) {
            auto t = ts.substr(0, i);
            if (s <= t && t <= e) {
                ans.emplace_back(id);
            }
        }
        return ans;
    }

private:
    vector<pair<int, string>> logs;
    unordered_map<string, int> d;
};

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem* obj = new LogSystem();
 * obj->put(id,timestamp);
 * vector<int> param_2 = obj->retrieve(start,end,granularity);
 */