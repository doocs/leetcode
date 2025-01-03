class MyCalendarTwo {
public:
    MyCalendarTwo() {
    }

    bool book(int startTime, int endTime) {
        ++m[startTime];
        --m[endTime];
        int s = 0;
        for (auto& [_, v] : m) {
            s += v;
            if (s > 2) {
                --m[startTime];
                ++m[endTime];
                return false;
            }
        }
        return true;
    }

private:
    map<int, int> m;
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo* obj = new MyCalendarTwo();
 * bool param_1 = obj->book(startTime,endTime);
 */
