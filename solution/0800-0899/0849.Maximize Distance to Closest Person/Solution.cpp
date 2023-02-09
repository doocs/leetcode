class Solution {
public:
    int maxDistToClosest(vector<int>& seats) {
        int first = -1, last = -1;
        int d = 0, n = seats.size();
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                if (last != -1) {
                    d = max(d, i - last);
                }
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return max({d / 2, max(first, n - last - 1)});
    }
};