class Solution {
public:
    int secondsBetweenTimes(string startTime, string endTime) {
        return f(endTime) - f(startTime);
    }

private:
    int f(const string& s) {
        return stoi(s.substr(0, 2)) * 3600
            + stoi(s.substr(3, 2)) * 60
            + stoi(s.substr(6));
    }
};