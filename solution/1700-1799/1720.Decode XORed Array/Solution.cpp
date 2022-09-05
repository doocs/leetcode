class Solution {
public:
    vector<int> decode(vector<int>& encoded, int first) {
        vector<int> ans{{first}};
        for (int i = 0; i < encoded.size(); ++i)
            ans.push_back(ans[i] ^ encoded[i]);
        return ans;
    }
};