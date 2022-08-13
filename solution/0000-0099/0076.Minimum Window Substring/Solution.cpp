class Solution {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> m;
        int begin = 0, end = 0, minlen = INT_MAX, minStart = 0, size = s.size(), counter = t.size();
        for (auto c : t)
            m[c]++;

        while (end < size) {
            if (m[s[end]] > 0)
                counter--;

            m[s[end]]--;

            end++;

            while (counter == 0) {
                if (end - begin < minlen) {
                    minStart = begin;
                    minlen = end - begin;
                }

                m[s[begin]]++;
                if (m[s[begin]] > 0)
                    counter++;

                begin++;
            }
        }

        if (minlen != INT_MAX) {
            return s.substr(minStart, minlen);
        }
        return "";
    }
};
