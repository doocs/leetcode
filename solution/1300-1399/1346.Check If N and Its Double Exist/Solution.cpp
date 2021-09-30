class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_map<int, int> map;
        for (int i = 0; i < arr.size(); ++i) {
            map[arr[i]] = i;
        }
        for (int i = 0; i < arr.size(); ++i) {
            if (map.find(arr[i] * 2) != map.end() && i != map[arr[i] * 2]) {
                return true;
            }
        }
        return false;
    }
};