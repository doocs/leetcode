class Solution {
public:
    bool isPathCrossing(string path) {
        int i = 0, j = 0;
        unordered_set<int> s{{0}};
        for (char& c : path) {
            if (c == 'N') {
                --i;
            } else if (c == 'S') {
                ++i;
            } else if (c == 'E') {
                ++j;
            } else {
                --j;
            }
            int t = i * 20000 + j;
            if (s.count(t)) {
                return true;
            }
            s.insert(t);
        }
        return false;
    }
};