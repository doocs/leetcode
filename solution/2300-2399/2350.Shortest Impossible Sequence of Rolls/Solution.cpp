class Solution {
public:
    int shortestSequence(vector<int>& rolls, int k) {
        unordered_set<int> s;
        int ans = 1;
        for (int v : rolls) {
            s.insert(v);
            if (s.size() == k) {
                s.clear();
                ++ans;
            }
        }
        return ans;
    }
};