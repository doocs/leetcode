class Solution {
public:
    int numberOfBeams(vector<string>& bank) {
        int ans = 0;
        int last = 0;
        for (auto& b : bank) {
            int t = 0;
            for (char& c : b)
                if (c == '1')
                    ++t;
            if (t) {
                ans += last * t;
                last = t;
            }
        }
        return ans;
    }
};