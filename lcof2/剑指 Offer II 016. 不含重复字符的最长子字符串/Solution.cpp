class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if (s.size() == 0)
            return 0;

        int left = 0;
        int maxlen = 0;
        unordered_set<char> hash;

        for (int right = 0; right < s.size(); right++) {
            while (hash.find(s[right]) != hash.end()) {
                hash.erase(s[left]);
                left++;
            }

            hash.insert(s[right]);
            maxlen = max(maxlen, right - left + 1);
        }

        return maxlen;
    }
};