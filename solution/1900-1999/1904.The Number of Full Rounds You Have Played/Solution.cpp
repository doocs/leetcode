class Solution {
public:
    int numberOfRounds(string startTime, string finishTime) {
        int start = get(startTime), finish = get(finishTime);
        if (start > finish) {
            finish += 24 * 60;
        }
        start = (start + 14) / 15;
        finish /= 15;
        return max(0, finish - start);
    }

private:
    int get(string s) {
        int a, b;
        sscanf(s.c_str(), "%d:%d", &a, &b);
        return a * 60 + b;
    }
};