class Solution {
public:
    bool makeEqual(vector<string>& words) {
        vector<int> counter(26, 0);
        for (string word : words) {
            for (char c : word) {
                ++counter[c - 'a'];
            }
        }
        int n = words.size();
        for (int count : counter) {
            if (count % n != 0) return false;
        }
        return true;
    }
};