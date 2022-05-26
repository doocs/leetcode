class Solution {
public:
    int trap(vector<int>& height) {
        int len = height.size();
        if(len == 0 || len == 1)return 0;
        
        int slow = 0;
        int fast = 1;
        
        int stopPoint;
        
        int total = 0;
        int bottom;
        int tmp = 0;
        while(fast < len){
            //每次更新stopPoint
            stopPoint = fast;
            while(fast < len && height[fast] <= height[slow]){
                if(height[fast] > height[stopPoint])
                    stopPoint = fast;
                fast++;
            }
            
            //越界了要回到stopPoint
            if(fast >= len)fast = stopPoint;

            tmp = 0;
            bottom = min(height[slow],height[fast]);
            for(int i = slow+1;i<fast;i++){
                tmp += bottom - height[i];
            }
            slow = fast;
            total += tmp;
            fast++;
        }
        return total;    
    }  
};