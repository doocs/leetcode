class Solution {
public:
    int countElements(vector<int>& arr) {
        unordered_set<int> s;
        for (int num : arr) s.insert(num);
        int res = 0;
        for (int num : arr)
            if (s.count(num + 1)) ++res;
        return res;
    }
};