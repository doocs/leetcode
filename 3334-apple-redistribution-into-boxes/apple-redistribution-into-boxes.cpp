class Solution {
public:
    int minimumBoxes(vector<int>& apple, vector<int>& c) {
        int total = 0;
        for(int i = 0; i<apple.size();i++) {
            total += apple[i];
        }
        int count = 0;
        sort(c.begin(),c.end());
        for(int i = c.size() - 1; i >= 0; i--) {
            if(total > 0) {
                total -= c[i];
                count++;
            }
        }
        return count;
    }
};