# [面试题 38. 字符串的排列](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

输入一个字符串，打印出该字符串中字符的所有排列。

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

**示例:**

```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```

**限制：**

- `1 <= s 的长度 <= 8`

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def permutation(self, s: str) -> List[str]:
        def dfs(x):
            if x == len(s) - 1:
                res.append("".join(chars))
                return
            t = set()
            for i in range(x, len(s)):
                if chars[i] in t:
                    continue
                t.add(chars[i])
                chars[i], chars[x] = chars[x], chars[i]
                dfs(x + 1)
                chars[i], chars[x] = chars[x], chars[i]
        chars, res = list(s), []
        dfs(0)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private char[] chars;
    private List<String> res;

    public String[] permutation(String s) {
        chars = s.toCharArray();
        res = new ArrayList<>();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    private void dfs(int x) {
        if (x == chars.length - 1) {
            res.add(String.valueOf(chars));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = x; i < chars.length; ++i) {
            if (set.contains(chars[i])) {
                continue;
            }
            set.add(chars[i]);
            swap(i, x);
            dfs(x + 1);
            swap(i, x);
        }
    }

    private void swap(int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string[]}
 */
var permutation = function (s) {
    let len = s.length;
    let res = new Set();
    function dfs(str, isRead) {
        if (str.length === len) {
            res.add(str);
            return;
        }
        for (let i = 0; i < len; i++) {
            if (isRead[i]) continue;
            isRead[i] = 1;
            dfs(str.concat(s[i]), isRead);
            isRead[i] = 0;
        }
    }
    dfs("", {});
    return [...res];
};
```

### **C++**

```cpp
class Solution {
public:
    void func(string str, int index, set<string>& mySet) {
        if (index == str.size()) {
            // 当轮训到最后一个字符的时候，直接放入set中。加入set结构，是为了避免插入的值重复
            mySet.insert(str);
        } else {
            for (int i = index; i < str.size(); i++) {
                // 从传入位置(index)开始算，固定第一个字符，然后后面的字符依次跟index位置交换
                swap(str[i], str[index]);
                int temp = index + 1;
                func(str, temp, mySet);
                swap(str[i], str[index]);
            }
        }
    }

    vector<string> permutation(string s) {
        set<string> mySet;
        func(s, 0, mySet);
        vector<string> ret;
        for (auto& x : mySet) {
            /* 这一题加入mySet是为了进行结果的去重。
               但由于在最后加入了将set转vector的过程，所以时间复杂度稍高 */
            ret.push_back(x);
        }
        return ret;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
