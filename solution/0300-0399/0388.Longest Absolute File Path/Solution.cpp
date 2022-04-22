class Solution {
public:
    int lengthLongestPath(string input) {
        int i = 0, n = input.size();
        int ans = 0;
        stack<int> stk;
        while (i < n) {
            int ident = 0;
            for (; input[i] == '\t'; ++i) {
                ++ident;
            }

            int cur = 0;
            bool isFile = false;
            for (; i < n && input[i] != '\n'; ++i) {
                ++cur;
                if (input[i] == '.') {
                    isFile = true;
                }
            }
            ++i;

            // popd
            while (!stk.empty() && stk.size() > ident) {
                stk.pop();
            }

            if (stk.size() > 0) {
                cur += stk.top() + 1;
            }

            // pushd
            if (!isFile) {
                stk.push(cur);
                continue;
            }

            ans = max(ans, cur);
        }
        return ans;
    }
};
