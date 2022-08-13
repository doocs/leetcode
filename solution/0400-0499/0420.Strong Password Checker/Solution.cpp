class Solution {
public:
    int strongPasswordChecker(string password) {
        int types = countTypes(password);
        int n = password.size();
        if (n < 6) return max(6 - n, 3 - types);
        if (n <= 20) {
            int replace = 0, cnt = 0;
            char prev = '~';
            for (char& curr : password) {
                if (curr == prev)
                    ++cnt;
                else {
                    replace += cnt / 3;
                    cnt = 1;
                    prev = curr;
                }
            }
            replace += cnt / 3;
            return max(replace, 3 - types);
        }
        int replace = 0, remove = n - 20;
        int remove2 = 0;
        int cnt = 0;
        char prev = '~';
        for (char& curr : password) {
            if (curr == prev)
                ++cnt;
            else {
                if (remove > 0 && cnt >= 3) {
                    if (cnt % 3 == 0) {
                        --remove;
                        --replace;
                    } else if (cnt % 3 == 1)
                        ++remove2;
                }
                replace += cnt / 3;
                cnt = 1;
                prev = curr;
            }
        }
        if (remove > 0 && cnt >= 3) {
            if (cnt % 3 == 0) {
                --remove;
                --replace;
            } else if (cnt % 3 == 1)
                ++remove2;
        }
        replace += cnt / 3;

        int use2 = min(min(replace, remove2), remove / 2);
        replace -= use2;
        remove -= use2 * 2;

        int use3 = min(replace, remove / 3);
        replace -= use3;
        remove -= use3 * 3;
        return (n - 20) + max(replace, 3 - types);
    }

    int countTypes(string& s) {
        int a = 0, b = 0, c = 0;
        for (char& ch : s) {
            if (islower(ch))
                a = 1;
            else if (isupper(ch))
                b = 1;
            else if (isdigit(ch))
                c = 1;
        }
        return a + b + c;
    }
};