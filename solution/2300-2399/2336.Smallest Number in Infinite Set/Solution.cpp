class SmallestInfiniteSet {
public:
    unordered_set<int> black;

    SmallestInfiniteSet() {
    }

    int popSmallest() {
        int i = 1;
        for (; black.count(i); ++i)
            ;
        black.insert(i);
        return i;
    }

    void addBack(int num) {
        black.erase(num);
    }
};

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet* obj = new SmallestInfiniteSet();
 * int param_1 = obj->popSmallest();
 * obj->addBack(num);
 */