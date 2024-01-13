class MovingAverage {
public:
    MovingAverage(int size) {
        n = size;
    }

    double next(int val) {
        if (q.size() == n) {
            s -= q.front();
            q.pop();
        }
        q.push(val);
        s += val;
        return (double) s / q.size();
    }

private:
    queue<int> q;
    int s = 0;
    int n;
};

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage* obj = new MovingAverage(size);
 * double param_1 = obj->next(val);
 */