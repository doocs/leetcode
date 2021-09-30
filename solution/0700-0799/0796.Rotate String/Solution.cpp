class Solution {
public:
    bool rotateString(string s, string goal) {
        if (s.size() != goal.size()) {
            return false;
        }
        return !!strstr((s + s).data(), goal.data());
    }
};