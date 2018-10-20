## 有效的括号
### 题目描述

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:
```
输入: "()"
输出: true
```

示例 2:
```
输入: "()[]{}"
输出: true
```

示例 3:
```
输入: "(]"
输出: false
```

示例 4:
```
输入: "([)]"
输出: false
```

示例 5:
```
输入: "{[]}"
输出: true
```

### 解法
遍历 string，遇到左括号，压入栈中；遇到右括号，从栈中弹出元素，元素不存在或者元素与该右括号不匹配，返回 false。遍历结束，栈为空则返回 true，否则返回 false。

因为字符串只包含"(){}[]",也可以进行特殊处理，用映射来做。

#### Java 版实现
```java
class Solution {
    public boolean isValid(String s) {
        if (s == null || s == "") {
            return true;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;

        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < n; ++i) {
            char a = chars[i];
            if (isLeft(a)) {
                stack.push(a);
            } else {
                if (stack.isEmpty() || !isMatch(stack.pop(), a)) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
                
    }
    
    private boolean isMatch(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}');
    }
    
    private boolean isLeft(char a) {
        return a == '(' || a == '[' || a == '{';
    }
    
    private boolean isRight(char a) {
        return a == ')' || a == ']' || a == '}';
    }
}
```

#### C++ 版实现
```cpp

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

// 特殊
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

```
