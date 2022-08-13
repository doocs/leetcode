class SummaryRanges {
private:
    map<int, vector<int>> mp;

public:
    SummaryRanges() {
    }

    void addNum(int val) {
        auto r = mp.upper_bound(val);
        auto l = r == mp.begin() ? mp.end() : prev(r);
        if (l != mp.end() && r != mp.end() && l->second[1] + 1 == val && r->second[0] - 1 == val) {
            l->second[1] = r->second[1];
            mp.erase(r);
        } else if (l != mp.end() && val <= l->second[1] + 1)
            l->second[1] = max(val, l->second[1]);
        else if (r != mp.end() && val >= r->second[0] - 1)
            r->second[0] = min(val, r->second[0]);
        else
            mp[val] = {val, val};
    }

    vector<vector<int>> getIntervals() {
        vector<vector<int>> res;
        for (auto& range : mp) res.push_back(range.second);
        return res;
    }
};

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges* obj = new SummaryRanges();
 * obj->addNum(val);
 * vector<vector<int>> param_2 = obj->getIntervals();
 */