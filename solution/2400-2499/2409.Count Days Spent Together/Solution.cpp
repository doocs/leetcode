class Solution {
public:
    vector<int> days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int countDaysTogether(string arriveAlice, string leaveAlice, string arriveBob, string leaveBob) {
        string a = arriveAlice < arriveBob ? arriveBob : arriveAlice;
        string b = leaveAlice < leaveBob ? leaveAlice : leaveBob;
        int x = f(a), y = f(b);
        return max(0, y - x + 1);
    }

    int f(string s) {
        int m, d;
        sscanf(s.c_str(), "%d-%d", &m, &d);
        int res = 0;
        for (int i = 0; i < m - 1; ++i) {
            res += days[i];
        }
        res += d;
        return res;
    }
};