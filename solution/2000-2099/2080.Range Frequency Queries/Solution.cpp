class RangeFreqQuery {
public:
    unordered_map<int, vector<int>> mp;
    RangeFreqQuery(vector<int>& arr) {
        for (int i = 0; i < arr.size(); ++i)
            mp[arr[i]].push_back(i);
    }

    int query(int left, int right, int value) {
        if (!mp.count(value)) return 0;
        auto& arr = mp[value];
        auto l = upper_bound(arr.begin(), arr.end(), left - 1);
        auto r = upper_bound(arr.begin(), arr.end(), right);
        return r - l;
    }
};

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery* obj = new RangeFreqQuery(arr);
 * int param_1 = obj->query(left,right,value);
 */