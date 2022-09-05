#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool isRectangleCover(vector<vector<int>>& rectangles) {
        long long area = 0;
        int minX = rectangles[0][0], minY = rectangles[0][1];
        int maxX = rectangles[0][2], maxY = rectangles[0][3];

        using pii = pair<int, int>;
        map<pii, int> cnt;

        for (auto& r : rectangles) {
            area += (r[2] - r[0]) * (r[3] - r[1]);

            minX = min(minX, r[0]);
            minY = min(minY, r[1]);
            maxX = max(maxX, r[2]);
            maxY = max(maxY, r[3]);

            ++cnt[{r[0], r[1]}];
            ++cnt[{r[0], r[3]}];
            ++cnt[{r[2], r[3]}];
            ++cnt[{r[2], r[1]}];
        }

        if (area != (long long) (maxX - minX) * (maxY - minY) || cnt[{minX, minY}] != 1 || cnt[{minX, maxY}] != 1 || cnt[{maxX, maxY}] != 1 || cnt[{maxX, minY}] != 1) {
            return false;
        }

        cnt.erase({minX, minY});
        cnt.erase({minX, maxY});
        cnt.erase({maxX, maxY});
        cnt.erase({maxX, minY});

        return all_of(cnt.begin(), cnt.end(), [](pair<pii, int> e) {
            return e.second == 2 || e.second == 4;
        });
    }
};
