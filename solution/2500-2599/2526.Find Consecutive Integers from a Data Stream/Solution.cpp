class DataStream {
public:
    DataStream(int value, int k) {
        val = value;
        this->k = k;
    }

    bool consec(int num) {
        cnt = num == val ? cnt + 1 : 0;
        return cnt >= k;
    }

private:
    int cnt = 0;
    int val, k;
};

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream* obj = new DataStream(value, k);
 * bool param_1 = obj->consec(num);
 */