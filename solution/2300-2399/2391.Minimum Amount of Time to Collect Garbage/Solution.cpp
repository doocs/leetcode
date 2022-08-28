class Solution {
public:
    vector<string> g;
    vector<int> travel;

    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        g = garbage;
        this->travel = travel;
        return f('M') + f('P') + f('G');   
    }

    int f(char c) {
        int tot = 0;
        for (string& v : g) {
            for (char ch : v) {
                tot += ch == c;
            }
        }
        int res = 0;
        for (int i = 0; i < g.size(); ++i) {
            int t = 0;
            for (char ch : g[i]) {
                t += ch == c;
            }
            res += t;
            tot -= t;
            if (tot == 0) break;
            if (i < g.size() - 1) res += travel[i];
        }
        return res;
    }
};