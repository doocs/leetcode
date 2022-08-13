class NumberContainers {
public:
    map<int, int> mp;
    map<int, set<int>> t;

    NumberContainers() {
    }

    void change(int index, int number) {
        auto it = mp.find(index);
        if (it != mp.end()) {
            t[it->second].erase(index);
            it->second = number;
        } else
            mp[index] = number;
        t[number].insert(index);
    }

    int find(int number) {
        auto it = t.find(number);
        return it == t.end() || it->second.empty() ? -1 : *it->second.begin();
    }
};

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers* obj = new NumberContainers();
 * obj->change(index,number);
 * int param_2 = obj->find(number);
 */