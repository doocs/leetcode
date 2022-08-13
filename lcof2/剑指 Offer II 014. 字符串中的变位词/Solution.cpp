class Solution {
public:
    bool checkInclusion(string s1, string s2) {

        int len1 = s1.size();
        int len2 = s2.size();

        if (len2 < len1) {
            return false;
        }

        int count[30] = {0};

        for (int i = 0; i < len1; ++i) {
            ++count[s1[i] - 'a'];
            --count[s2[i] - 'a'];
        }

        int l = 0;
        int r = len1 - 1;

        while (r < len2) {

            bool flag = true;

            for (int i : count) {
                if (i != 0) {
                    flag = false;
                }
            }

            if (flag) {
                return true;
            }

            if (r + 1 >= len2) {
                break;
            }

            ++count[s2[l++] - 'a'];
            --count[s2[++r] - 'a'];
        }

        return false;
    }
};