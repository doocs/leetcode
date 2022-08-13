class MapSum {
public:
    unordered_map<string, int> data;
    unordered_map<string, int> t;

    /** Initialize your data structure here. */
    MapSum() {
    }

    void insert(string key, int val) {
        int old = t[key];
        t[key] = val;
        for (int i = 1; i < key.size() + 1; ++i) {
            string k = key.substr(0, i);
            data[k] += (val - old);
        }
    }

    int sum(string prefix) {
        return data[prefix];
    }
};

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum* obj = new MapSum();
 * obj->insert(key,val);
 * int param_2 = obj->sum(prefix);
 */