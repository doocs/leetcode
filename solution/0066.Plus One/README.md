## 加一

### 问题描述

给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

```
示例 1:
输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。

示例 2:
输入: [4,3,2,1]
输出: [4,3,2,2]
解释: 输入数组表示数字 4321。
```

### 思路：
1. 末尾加1，注意超过10的情况，要取余进1
2. 前后关系式应该是`digits[i-1] = digits[i-1] + digits[i] / 10;`，即前一个元素的值应该是本身加上后一个元素的进位
3. 若首元素>=10，则需要插入元素

```CPP
class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int len = digits.size();
        if(len == 0)return digits;
        digits[len-1]++;
        int num = digits[len - 1];
        for(int i = len - 1;i>=1;i--){
            digits[i-1] = digits[i-1] + digits[i]/10;
            digits[i] %= 10;
        }

        if(digits[0] >= 10){
            digits.insert(digits.begin(),digits[0]/10);
            digits[1] = digits[1] % 10;   
        }
        return digits;
    }
};

```