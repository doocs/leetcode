class LUPrefix {
public:
    LUPrefix(int n) {
    }

    void upload(int video) {
        s.insert(video);
        while (s.count(r + 1)) {
            ++r;
        }
    }

    int longest() {
        return r;
    }

private:
    int r = 0;
    unordered_set<int> s;
};

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix* obj = new LUPrefix(n);
 * obj->upload(video);
 * int param_2 = obj->longest();
 */