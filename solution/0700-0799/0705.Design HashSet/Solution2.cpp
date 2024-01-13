class MyHashSet {
private:
    int size = 1000;
    vector<list<int>> data;

public:
    MyHashSet()
        : data(size) {
    }

    void add(int key) {
        if (contains(key)) {
            return;
        }
        int idx = hash(key);
        data[idx].push_back(key);
    }

    void remove(int key) {
        if (!contains(key)) {
            return;
        }
        int idx = hash(key);
        data[idx].remove(key);
    }

    bool contains(int key) {
        int idx = hash(key);
        for (auto it = data[idx].begin(); it != data[idx].end(); it++) {
            if ((*it) == key) {
                return true;
            }
        }
        return false;
    }

    int hash(int key) {
        return key % size;
    }
};

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet* obj = new MyHashSet();
 * obj->add(key);
 * obj->remove(key);
 * bool param_3 = obj->contains(key);
 */