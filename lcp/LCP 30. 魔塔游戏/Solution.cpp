class Solution {
public:
    int magicTower(vector<int>& nums) {
        priority_queue<int, vector<int>, greater<int>> q;
        int64_t blood = 1, v = 0;
        int ans = 0;
        for (int x : nums) {
            if (x < 0) {
                q.push(x);
            }
            blood += x;
            if (blood <= 0) {
                ++ans;
                v += q.top();
                blood -= q.top();
                q.pop();
            }
        }
        blood += v;
        return blood <= 0 ? -1 : ans;
    }
};