class Solution {
public:
    int rampartDefensiveLine(vector<vector<int>>& rampart) {
        int left = 0, right = rampart[1][0] - rampart[0][1] + rampart[2][0] - rampart[1][1];
        auto check = [&](int w) {
            int last = rampart[0][1];
            for (int i = 1; i < rampart.size() - 1; ++i) {
                int x = rampart[i][0], y = rampart[i][1];
                int a = x - last;
                int b = max(w - a, 0);
                if (y + b > rampart[i + 1][0]) {
                    return false;
                }
                last = y + b;
            }
            return true;
        };

        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};