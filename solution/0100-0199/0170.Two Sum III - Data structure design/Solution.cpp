class TwoSum {
public:
    TwoSum() {
    }

    void add(int number) {
        ++cnt[number];
    }

    bool find(int value) {
        for (auto& [x, v] : cnt) {
            long y = (long) value - x;
            if (cnt.count(y)) {
                if (x != y || v > 1) {
                    return true;
                }
            }
        }
        return false;
    }

private:
    unordered_map<int, int> cnt;
};

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum* obj = new TwoSum();
 * obj->add(number);
 * bool param_2 = obj->find(value);
 */