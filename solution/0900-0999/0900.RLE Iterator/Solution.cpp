class RLEIterator {
public:
    RLEIterator(vector<int>& encoding) {
        this->encoding = encoding;
    }

    int next(int n) {
        while (i < encoding.size()) {
            if (encoding[i] - j < n) {
                n -= (encoding[i] - j);
                i += 2;
                j = 0;
            } else {
                j += n;
                return encoding[i + 1];
            }
        }
        return -1;
    }

private:
    vector<int> encoding;
    int i = 0;
    int j = 0;
};

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator* obj = new RLEIterator(encoding);
 * int param_1 = obj->next(n);
 */