class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_set<int> s;
        for (int& v : arr) {
            if (s.count(v * 2) || (v % 2 == 0 && s.count(v / 2))) {
                return true;
            }
            s.insert(v);
        }
        return false;
    }
};