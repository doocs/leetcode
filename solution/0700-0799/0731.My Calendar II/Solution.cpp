class MyCalendarTwo {
public:
    map<int, int> m;

    MyCalendarTwo() {
    }

    bool book(int start, int end) {
        ++m[start];
        --m[end];
        int s = 0;
        for (auto& [_, v] : m) {
            s += v;
            if (s > 2) {
                --m[start];
                ++m[end];
                return false;
            }
        }
        return true;
    }
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(start,end);
 */