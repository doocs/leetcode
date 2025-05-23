
class Solution {
public:
   
    static int comp(vector<int>&a,vector<int>&b)
    {  
        return a[1]>b[1];
    }
    int maximumUnits(vector<vector<int>>& boxTypes, int truck) {
       int n=boxTypes.size();
        sort(boxTypes.begin(),boxTypes.end(),comp);
        int ans=0;
        for(int i=0;i<n;i++)
        {
       
            if(truck-boxTypes[i][0]>=0)
            {
               ans+=(boxTypes[i][0] * boxTypes[i][1]); 
                truck-=boxTypes[i][0];
            }
            else
            {
               ans+=(truck * boxTypes[i][1]); 
               break; 
            }
        }
        return ans;
    }
};