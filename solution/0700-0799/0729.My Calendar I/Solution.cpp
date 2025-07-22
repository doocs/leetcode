class MyCalendar {
public:
    MyCalendar() {
    }

    bool book(int startTime, int endTime) {
        auto e = m.lower_bound(startTime + 1);
        if (e != m.end() && e->second < endTime) {
            return false;
        }
        m[endTime] = startTime;
        return true;
    }

private:
    map<int, int> m;
};

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar* obj = new MyCalendar();
 * bool param_1 = obj->book(startTime,endTime);
 */
