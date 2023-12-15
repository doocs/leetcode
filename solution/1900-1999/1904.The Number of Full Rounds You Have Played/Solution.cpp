class Solution {
public:
    int numberOfRounds(string loginTime, string logoutTime) {
        auto f = [](string& s) {
            int h, m;
            sscanf(s.c_str(), "%d:%d", &h, &m);
            return h * 60 + m;
        };
        int a = f(loginTime), b = f(logoutTime);
        if (a > b) {
            b += 1440;
        }
        return max(0, b / 15 - (a + 14) / 15);
    }
};