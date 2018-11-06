class Solution {
public:
    bool isValid(string s) {
        stack<char> _stack;
        int len = s.length();
        if(len == 0)return true;
        char ch;
        for(int i= 0;i<len;i++)
        {
            if(s[i] == '{' ||s[i] == '['||s[i] == '(' )
            {
                _stack.push(s[i]);
            }
            if(s[i] == '}')
            {
                if(_stack.empty())return false;
                else ch = _stack.top();
                
                if(ch != '{')return false;
                else _stack.pop();
                
            }
            else if(s[i] == ']')
            {
                if(_stack.empty())return false;
                else ch = _stack.top();
                
                if(ch != '[')return false;
                else _stack.pop();
            }
            else if(s[i] == ')')
            {
                if(_stack.empty())return false;
                else ch = _stack.top();
                
                if(ch != '(')return false;
                else _stack.pop();
            }         
        }
        
        if(!_stack.empty())return false;
        
        return true;
        
    }
};



-----------------
//特殊
class Solution {
public:
    bool isValid(string s) {
        map<char,int> m={
            {'[',1},
            {']',-1},
            {'{',2},
            {'}',-2},
            {'(',3},
            {')',-3}
        };
        stack<int> sk;
        for(int i=0;i<s.length();i++){
            if(m[s[i]]<0 ){
                if(!sk.empty() && sk.top()==(-m[s[i]])){
                    sk.pop();
                }else{
                    return false;
                }
            }else{
                sk.push(m[s[i]]);
            }
        }
        if(sk.empty())
            return true;
        return false;
    }
};
