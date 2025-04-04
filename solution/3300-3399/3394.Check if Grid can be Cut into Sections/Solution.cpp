class Solution {
#define pii pair<int, int>

    bool countLineIntersections(vector<pii>& coordinates) {
        int lines = 0;
        int overlap = 0;
        for (int i = 0; i < coordinates.size(); ++i) {
            if (coordinates[i].second == 0)
                overlap--;
            else
                overlap++;
            if (overlap == 0)
                lines++;
        }
        return lines >= 3;
    }

public:
    bool checkValidCuts(int n, vector<vector<int>>& rectangles) {
        vector<pii> y_cordinates, x_cordinates;
        for (auto& rectangle : rectangles) {
            y_cordinates.push_back(make_pair(rectangle[1], 1));
            y_cordinates.push_back(make_pair(rectangle[3], 0));
            x_cordinates.push_back(make_pair(rectangle[0], 1));
            x_cordinates.push_back(make_pair(rectangle[2], 0));
        }
        sort(y_cordinates.begin(), y_cordinates.end());
        sort(x_cordinates.begin(), x_cordinates.end());

        // Line-Sweep on x and y cordinates
        return (countLineIntersections(y_cordinates) or countLineIntersections(x_cordinates));
    }
};