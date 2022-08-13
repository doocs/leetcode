# [面试题 17. 打印从 1 到最大的 n 位数](https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

## 题目描述

<p>输入数字 <code>n</code>，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> n = 1
<strong>输出:</strong> [1,2,3,4,5,6,7,8,9]
</pre>

<p>&nbsp;</p>

<p>说明：</p>

<ul>
	<li>用返回一个整数列表来代替打印</li>
	<li>n 为正整数</li>
</ul>

## 解法

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def printNumbers(self, n: int) -> List[int]:
        return [i for i in range(1, 10**n)]
```

### **Java**

```java
class Solution {
    public int[] printNumbers(int n) {
        n = (int) Math.pow(10, n) - 1;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = i + 1;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var printNumbers = function (n) {
    let res = [];
    for (let i = 1; i < 10 ** n; ++i) {
        res.push(i);
    }
    return res;
};
```

### **Go**

```go
func printNumbers(n int) []int {
    d := 10
    for i := 1; i < n; i++ {
        d *= 10
    }
    res := make([]int, d - 1)
    for i := 1; i < d; i++ {
        res[i - 1] = i
    }
    return res
}
```

### **C++**

假设 `n` 很大，则需要用 dfs 枚举，并且返回字符串

```cpp
class Solution {
public:
    vector<int> printNumbers(int n) {
        vector<int> ans;
        string s;
        dfs(ans, s, 0, n);
        return ans;
    }

    void dfs(vector<int>& ans, string& s, int k, int n) {
        if (k == n) {
            int num = atoi(s.c_str());
            if (num) ans.push_back(num);
            return;
        }
        for (int i = 0; i <= 9; ++i) {
            s += i + '0';
            dfs(ans, s, k + 1, n);
            s.pop_back();
        }
    }
};
```

### **C#**

```cs
public class Solution {
    public int[] PrintNumbers(int n) {
        List<int> ans = new List<int>();
        for (int i = 0; i < Math.Pow(10, n); i++)
        {
            ans.Add(i);
        }
        return ans.ToArray();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
