class Solution {
public:
    bool uniqueOccurrences(vector<int>& arr) {
        unordered_map<int, int> counter;
        for (auto e : arr) {
            ++counter[e];
        }
        unordered_set<int> s;
        for (auto e : counter) {
            int num = e.second;
            if (s.count(num)) return false;
            s.insert(num);
        }
        return true;
    }
};