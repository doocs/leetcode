class Solution {
public:
    int strToInt(string str) {
        int res = 0, bndry = INT_MAX / 10;
        int i = 0, sign = 1, length = str.size();
        if (length == 0) {
            return 0;
        }
        // 删除首部空格
        while (str[i] == ' ') {
            if (++i == length) {
                return 0;
            }
        }
        // 若有负号则标识符号位
        if (str[i] == '-') {
            sign = -1;
        }
        if (str[i] == '-' || str[i] == '+') {
            i++;
        }
        for (int j = i; j < length; j++) {
            if (str[j] < '0' || str[j] > '9') {
                break;
            }
            // res>214748364越界；res=214748364且str[j] > '7'越界
            if (res > bndry || res == bndry && str[j] > '7') {
                return sign == 1 ? INT_MAX : INT_MIN;
            }
            // 从左向右遍历数字并更新结果
            res = res * 10 + (str[j] - '0');
        }
        return sign * res;
    }
};
