static int x=[](){
    std::ios::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();
string Compare(string s1,string s2)
{
    if(s1.size()==0||s2.size()==0)
        return "";
    int num=s1.size()<s2.size()?s1.size():s2.size();
    string s;
    for(int i=0;i<num;i++)
    {
        if(s1[i]==s2[i])
        {
            s.push_back(s1[i]);
        }
        else
            break;
        
    }
    return s;
}
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if(strs.size()==0)
            return "";
        string prefix=strs[0];
        for(int i=1;i<strs.size();i++)
        {
            prefix=Compare(prefix,strs[i]);
        }
        return prefix;
        
    }
};
