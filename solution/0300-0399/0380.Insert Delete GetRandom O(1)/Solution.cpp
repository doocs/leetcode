class RandomizedSet {
private:
    unordered_map<int, int> mp;
    vector<int> nums;

public:
    RandomizedSet() {
    }

    bool insert(int val) {
        if (mp.count(val)) return false;
        mp[val] = nums.size();
        nums.push_back(val);
        return true;
    }

    bool remove(int val) {
        if (!mp.count(val)) return false;
        int idx = mp[val];
        nums[idx] = nums.back();
        mp[nums.back()] = idx;
        mp.erase(val);
        nums.pop_back();
        return true;
    }

    int getRandom() {
        return nums[rand() % nums.size()];
    }
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */