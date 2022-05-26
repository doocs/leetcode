class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        if(heights.empty())return 0;
        int len = heights.size();
        
        stack<int> s_stack;
        int ans = 0;
        
        for(int i = 0;i < len;i++){
            if(s_stack.empty() || heights[i] >= s_stack.top())
            {//满足升序条件
                s_stack.push(heights[i]);
            }
            else
            {//不满足升序
                int count = 0;
                while(!s_stack.empty() && s_stack.top() > heights[i])
                {
                    count++;
                    ans = max(ans,s_stack.top() * count);
                    s_stack.pop();
                }
                while(count > 0)
                {
                    s_stack.push(heights[i]);
                    count--;
                }
                s_stack.push(heights[i]);
            }
        }
        
        int count = 1;
        while(!s_stack.empty()){
            ans = max(ans,s_stack.top() * count);
            s_stack.pop();
            count++;
        }
        
        return ans;
    }
};