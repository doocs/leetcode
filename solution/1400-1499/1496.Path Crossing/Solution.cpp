class Solution {
public:
    bool isPathCrossing(string path) {
        int x = 0, y = 0;
        unordered_set<int> vis {{0}};
        for (char c : path) {
            if (c == 'N')
                ++y;
            else if (c == 'S')
                --y;
            else if (c == 'E')
                ++x;
            else
                --x;
            int t = x * 20000 + y;
            if (vis.count(t)) return 1;
            vis.insert(t);
        }
        return 0;
    }
};