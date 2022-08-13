class Solution {
public:
    int findString(vector<string>& words, string s) {
        int left = 0, right = words.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            while (left < mid && words[mid] == "") --mid;
            if (s <= words[mid])
                right = mid;
            else
                left = mid + 1;
        }
        return words[left] == s ? left : -1;
    }
};