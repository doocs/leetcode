class Solution {
public:
    int maximumGain(string s, int x, int y) {
        if (x < y) {
            reverse(s.begin(), s.end());
            return maximumGain(s, y, x);
        }
        int ans = 0;
        stack<char> stk1;
        stack<char> stk2;
        for (char c : s) {
            if (c != 'b')
                stk1.push(c);
            else {
                if (!stk1.empty() && stk1.top() == 'a') {
                    stk1.pop();
                    ans += x;
                } else
                    stk1.push(c);
            }
        }
        while (!stk1.empty()) {
            char c = stk1.top();
            stk1.pop();
            if (c != 'b')
                stk2.push(c);
            else {
                if (!stk2.empty() && stk2.top() == 'a') {
                    stk2.pop();
                    ans += y;
                } else
                    stk2.push(c);
            }
        }
        return ans;
    }
};