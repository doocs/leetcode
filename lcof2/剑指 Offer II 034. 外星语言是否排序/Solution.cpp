class Solution {
public:
    bool isAlienSorted(vector<string>& words, string order) {
        vector<int> index(26);
        for (int i = 0; i < index.size(); ++i)
            index[order[i] - 'a'] = i;
        for (int i = 0; i < words.size() - 1; ++i) {
            string w1 = words[i];
            string w2 = words[i + 1];
            int l1 = w1.size(), l2 = w2.size();
            for (int j = 0; j < max(l1, l2); ++j) {
                int i1 = j >= l1 ? -1 : index[w1[j] - 'a'];
                int i2 = j >= l2 ? -1 : index[w2[j] - 'a'];
                if (i1 > i2)
                    return false;
                if (i1 < i2)
                    break;
            }
        }
        return true;
    }
};