class Solution {
public:
    int maxArea(vector<int>& height) {
        int Max = -1, Min, area;
        int s = 0, e = height.size()-1;
        
        while( s < e ){
            if( height[s] < height[e] ){
                area = (e-s) * height[s];
                s++;
            }
            else{
                area = (e-s) * height[e];
                e--;
            }
            if( area > Max ) Max = area;
        }
        return Max;
    }
};
