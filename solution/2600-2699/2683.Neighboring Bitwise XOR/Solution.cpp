class Solution {
public:
    bool doesValidArrayExist(vector<int>& derived) {
        int s = 0;
        for (int x : derived) {
            s ^= x;
        }
        return s == 0;
    }
};