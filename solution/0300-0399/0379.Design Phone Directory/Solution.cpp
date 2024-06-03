class PhoneDirectory {
public:
    PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; ++i) {
            available.insert(i);
        }
    }
    
    int get() {
        if (available.empty()) {
            return -1;
        }
        int x = *available.begin();
        available.erase(x);
        return x;
    }
    
    bool check(int number) {
        return available.contains(number);
    }
    
    void release(int number) {
        available.insert(number);
    }

private:
    unordered_set<int> available;
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */