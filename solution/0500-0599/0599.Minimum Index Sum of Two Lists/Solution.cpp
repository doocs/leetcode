class Solution {
public:
    vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
        unordered_map<string, int> d;
        for (int i = 0; i < list2.size(); ++i) {
            d[list2[i]] = i;
        }
        vector<string> ans;
        int mi = INT_MAX;
        for (int i = 0; i < list1.size(); ++i) {
            if (d.contains(list1[i])) {
                int j = d[list1[i]];
                if (i + j < mi) {
                    mi = i + j;
                    ans.clear();
                    ans.push_back(list1[i]);
                } else if (i + j == mi) {
                    ans.push_back(list1[i]);
                }
            }
        }
        return ans;
    }
};
