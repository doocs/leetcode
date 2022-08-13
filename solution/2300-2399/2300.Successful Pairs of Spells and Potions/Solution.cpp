class Solution {
public:
    vector<int> successfulPairs(vector<int>& spells, vector<int>& potions, long long success) {
        sort(potions.begin(), potions.end());
        int m = potions.size();
        vector<int> ans;
        for (int& s : spells) {
            int left = 0, right = m;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (1ll * s * potions[mid] >= success)
                    right = mid;
                else
                    left = mid + 1;
            }
            ans.push_back(m - left);
        }
        return ans;
    }
};