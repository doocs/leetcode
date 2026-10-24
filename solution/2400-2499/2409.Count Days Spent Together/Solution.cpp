class Solution {
public:
    static constexpr int f[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    int countDaysTogether(string arriveAlice, string leaveAlice, string arriveBob, string leaveBob) {
        string start = max(arriveAlice, arriveBob);
        string end = min(leaveAlice, leaveBob);
        int d1 = f[stoi(start.substr(0, 2)) - 1] + stoi(start.substr(3));
        int d2 = f[stoi(end.substr(0, 2)) - 1] + stoi(end.substr(3));
        return max(d2 - d1 + 1, 0);
    }
};