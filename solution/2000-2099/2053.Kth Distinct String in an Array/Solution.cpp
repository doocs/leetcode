class Solution {
public:
    string kthDistinct(vector<string>& arr, int k) {
        unordered_map<string, int> counter;
        for (auto& v : arr) ++counter[v];
        for (auto& v : arr) {
            if (counter[v] == 1) {
                --k;
                if (k == 0) return v;
            }
        }
        return "";
    }
};