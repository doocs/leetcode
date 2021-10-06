class Solution {
public:
    int maxDepth(string s) {
        int res = 0, depth =0;
        for (char c : s)
        {
            if (c == '(') res = max(res, ++depth);
            else if (c == ')') --depth;
        }
        return res;
    }
};