class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        vector<int> res;
        vector<int> hash(26, 0), zero(26, 0);

        if (p.size() > s.size())
            return res;

        for (int i = 0; i < p.size(); i++) {
            hash[p[i] - 'a']++;
            hash[s[i] - 'a']--;
        }

        if (hash == zero)
            res.push_back(0);

        for (int i = p.size(); i < s.size(); i++) {
            hash[s[i] - 'a']--;
            hash[s[i - p.size()] - 'a']++;

            if (hash == zero)
                res.push_back(i - p.size() + 1);
        }

        return res;
    }
};