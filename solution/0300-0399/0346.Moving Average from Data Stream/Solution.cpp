class MovingAverage {
public:
    MovingAverage(int size) {
        data.resize(size);
    }

    double next(int val) {
        int i = cnt % data.size();
        s += val - data[i];
        data[i] = val;
        ++cnt;
        return s * 1.0 / min(cnt, (int) data.size());
    }

private:
    int s = 0;
    int cnt = 0;
    vector<int> data;
};

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage* obj = new MovingAverage(size);
 * double param_1 = obj->next(val);
 */