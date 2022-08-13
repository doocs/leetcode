class RLEIterator {
public:
    vector<int> encoding;
    int curr;
    int i;

    RLEIterator(vector<int>& encoding) {
        this->encoding = encoding;
        this->curr = 0;
        this->i = 0;
    }

    int next(int n) {
        while (i < encoding.size()) {
            if (curr + n > encoding[i]) {
                n -= encoding[i] - curr;
                curr = 0;
                i += 2;
            } else {
                curr += n;
                return encoding[i + 1];
            }
        }
        return -1;
    }
};

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator* obj = new RLEIterator(encoding);
 * int param_1 = obj->next(n);
 */