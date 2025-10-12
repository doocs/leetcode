class ExamTracker {
public:
    ExamTracker() {
        times.push_back(0);
        pre.push_back(0LL);
    }

    void record(int time, int score) {
        times.push_back(time);
        pre.push_back(pre.back() + score);
    }

    long long totalScore(int startTime, int endTime) {
        int l = lower_bound(times.begin(), times.end(), startTime) - times.begin() - 1;
        int r = lower_bound(times.begin(), times.end(), endTime + 1) - times.begin() - 1;
        return pre[r] - pre[l];
    }

private:
    vector<int> times;
    vector<long long> pre;
};

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker* obj = new ExamTracker();
 * obj->record(time,score);
 * long long param_2 = obj->totalScore(startTime,endTime);
 */
