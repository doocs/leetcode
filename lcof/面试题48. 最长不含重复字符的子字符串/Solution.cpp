class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int arr[1024];    // 本题的用例中，有不为小写字母的情况
        for (int i = 0; i < 1024; i++) {
            arr[i] = -1;
        }

        int curLen = 0;
        int maxLen = 0;

        int len = s.size();
        for (int i = 0; i < len; i++) {
            int prev = arr[int(s[i])];    // 之前位置的index
            if (prev < 0 || i - prev > curLen) {
                // 其中，prev>0表示之前没有遇到过该字符
                // i - prev > curLen 表示之前遇到的当前字符，远超当前限定的范围
                // 这两种情况下，都是直接继续加就可以了
                curLen++;
            } else {
                if (curLen > maxLen) {
                    maxLen = curLen;
                }
                curLen = i - prev;    // curLen重新开始计数
            }

            arr[int(s[i])] = i;
        }

        return maxLen > curLen ? maxLen : curLen;
    }
};
