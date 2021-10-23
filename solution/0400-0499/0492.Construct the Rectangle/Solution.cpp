class Solution {
public:
    vector<int> constructRectangle(int area) {
        int w = sqrt(1.0 * area);
        while (area % w != 0) --w;
        return {area / w, w};
    }
};