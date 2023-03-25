class Solution {
public:
    bool isTransformable(string s, string t) {
        queue<int> pos[10];
        for (int i = 0; i < s.size(); ++i) {
            pos[s[i] - '0'].push(i);
        }
        for (char& c : t) {
            int x = c - '0';
            if (pos[x].empty()) {
                return false;
            }
            for (int j = 0; j < x; ++j) {
                if (!pos[j].empty() && pos[j].front() < pos[x].front()) {
                    return false;
                }
            }
            pos[x].pop();
        }
        return true;
    }
};