class Solution {
public:
    string simplifyPath(string path) {
        deque<string> stk;
        string res, tmp;
        stringstream ss(path);
        while (getline(ss, tmp, '/')) {
            if (tmp == "" || tmp == ".") continue;
            if (tmp == "..") {
                if (!stk.empty())
                    stk.pop_back();
            } else
                stk.push_back(tmp);
        }
        for (auto str : stk)
            res += "/" + str;
        return res.empty() ? "/" : res;
    }
};
