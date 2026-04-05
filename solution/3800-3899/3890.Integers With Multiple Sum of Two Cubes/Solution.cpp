vector<int> GOOD;

auto init = [] {
    const int LIMIT = 1e9;

    unordered_map<int, int> cnt;
    vector<int> cubes(1001);

    for (int i = 0; i <= 1000; ++i) {
        cubes[i] = i * i * i;
    }

    for (int a = 1; a <= 1000; ++a) {
        for (int b = a; b <= 1000; ++b) {
            int x = cubes[a] + cubes[b];
            if (x > LIMIT) break;
            cnt[x]++;
        }
    }

    for (auto& [x, v] : cnt) {
        if (v > 1) {
            GOOD.push_back(x);
        }
    }

    sort(GOOD.begin(), GOOD.end());

    return 0;
}();

class Solution {
public:
    vector<int> findGoodIntegers(int n) {
        int idx = upper_bound(GOOD.begin(), GOOD.end(), n) - GOOD.begin();
        return vector<int>(GOOD.begin(), GOOD.begin() + idx);
    }
};
