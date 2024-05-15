class RangeFreqQuery {
public:
    RangeFreqQuery(vector<int>& arr) {
        for (int i = 0; i < arr.size(); ++i) {
            g[arr[i]].push_back(i);
        }
    }

    int query(int left, int right, int value) {
        if (!g.contains(value)) {
            return 0;
        }
        auto& idx = g[value];
        auto l = lower_bound(idx.begin(), idx.end(), left);
        auto r = lower_bound(idx.begin(), idx.end(), right + 1);
        return r - l;
    }

private:
    unordered_map<int, vector<int>> g;
};

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery* obj = new RangeFreqQuery(arr);
 * int param_1 = obj->query(left,right,value);
 */