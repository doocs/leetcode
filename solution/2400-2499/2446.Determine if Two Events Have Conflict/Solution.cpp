class Solution {
public:
    bool haveConflict(vector<string>& event1, vector<string>& event2) {
        return event1[0] <= event2[1] && event1[1] >= event2[0];
    }
};