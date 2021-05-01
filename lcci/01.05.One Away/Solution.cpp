class Solution {
public:
    bool oneEditAway(string first, string second) {
        int n1 = first.size(), n2 = second.size();
        int diff = n1 - n2;
        if (abs(diff) > 1) {
            return false;
        }
        int op = 1;
        for (int i = 0, j = 0; i < n1 && j < n2; ++i, ++j) {
            bool notSame = first[i] != second[j];
            if (notSame) {
                if (diff == 1) {
                    --j;
                } else if (diff == -1) {
                    --i;
                }
                --op;
            }
            if (op < 0) {
                return false;
            }
        }
        return true;
    }
};