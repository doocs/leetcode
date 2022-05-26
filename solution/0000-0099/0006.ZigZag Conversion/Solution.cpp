// @ID:6. ZigZag Conversion
// @author:jxdeng3989

class Solution {
public:
    string convert(string s, int numRows) {
        string retstr;
        if(1==numRows)
            return s;
        for(int i=0; i<numRows; ++i)
        {
            retstr.push_back(s[i]);
            int maxspan = 2*(numRows-1);
            int span1 = maxspan-i*2;
            int span2 = maxspan - span1;
             int cntpos = i;
            if(span1==0)
                span1 = span2;
            if(span2==0)
                span2 = span1;
             while(1)
             {   
				 if(cntpos+span1>=s.size())
					 break;
                 cntpos += span1;
                 retstr.push_back(s[cntpos]);
                 
                 if(cntpos+span2>=s.size())
                     break;
                 cntpos += span2;
                 retstr.push_back(s[cntpos]);
             }
        }
        return retstr;
    }
};