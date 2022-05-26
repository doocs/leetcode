class MovingAverage {
public:
    /** Initialize your data structure here. */
    MovingAverage(int size) {
        this->size = size;
        data.resize(size);
    }
    
    double next(int val) {
        int idx = count % size;
        int oldVal = data[idx];
        data[idx] = val;
        sum += val - oldVal;
        ++count;
        return (double) sum / min(count, size);
    }

private:
    int size = 0;
    vector<int> data;
    int sum = 0;
    int count = 0;
};

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage* obj = new MovingAverage(size);
 * double param_1 = obj->next(val);
 */