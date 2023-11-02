class Solution {
public:
    int countPoints(string rings) {
        int d['Z']{['R'] = 1, ['G'] = 2, ['B'] = 4};
        int mask[10]{};
        for (int i = 0, n = rings.size(); i < n; i += 2) {
            int c = rings[i];
            int j = rings[i + 1] - '0';
            mask[j] |= d[c];
        }
        return count(mask, mask + 10, 7);
    }
};