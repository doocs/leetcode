class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        int maxarea = 0;
        stack<int> s;
        heights.insert(heights.begin(), 0);
        heights.push_back(0);

        for (int i = 0; i < heights.size(); i++) {
            while (!s.empty() && heights[i] < heights[s.top()]) {
                int h = heights[s.top()];
                s.pop();
                maxarea = max(maxarea, h * (i - s.top() - 1));
            }

            s.push(i);
        }

        return maxarea;
    }
};