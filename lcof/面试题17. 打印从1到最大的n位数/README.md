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

**方法一：模拟**

直接根据题意模拟即可。

时间复杂度 $O(10^n)$，空间复杂度 $O(1)$。

如果 $n$ 的值比较大，那么直接使用整数会溢出，因此可以使用字符串来模拟，参考以下代码中的 `print()` 函数。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def printNumbers(self, n: int) -> List[int]:
        return list(range(1, 10**n))

    def print(self, n: int) -> List[str]:
        def dfs(i, j):
            if i == j:
                ans.append("".join(s))
                return
            k = 0 if i else 1
            while k < 10:
                s.append(str(k))
                dfs(i + 1, j)
                s.pop()
                k += 1

        ans = []
        s = []
        for i in range(1, n + 1):
            dfs(0, i)
        return ans
```

### **Java**

```java
class Solution {
    public int[] printNumbers(int n) {
        int[] ans = new int[(int) Math.pow(10, n) - 1];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = i + 1;
        }
        return ans;
    }

    private StringBuilder s = new StringBuilder();
    private List<String> ans = new ArrayList<>();

    public List<String> print(int n) {
        for (int i = 1; i <= n; ++i) {
            dfs(0, i);
        }
        return ans;
    }

    private void dfs(int i, int j) {
        if (i == j) {
            ans.add(s.toString());
            return;
        }
        int k = i > 0 ? 0 : 1;
        for (; k < 10; ++k) {
            s.append(k);
            dfs(i + 1, j);
            s.deleteCharAt(s.length() - 1);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> printNumbers(int n) {
        vector<int> ans(pow(10, n) - 1);
        iota(ans.begin(), ans.end(), 1);
        return ans;
    }

    vector<string> print(int n) {
        vector<string> ans;
        string s;
        function<void(int, int)> dfs = [&](int i, int j) {
            if (i == j) {
                ans.push_back(s);
                return;
            }
            int k = i ? 0 : 1;
            for (; k < 10; ++k) {
                s.push_back(k + '0');
                dfs(i + 1, j);
                s.pop_back();
            }
        };
        for (int i = 1; i <= n; ++i) {
            dfs(0, i);
        }
        return ans;
    }
};
```

### **Go**

```go
func printNumbers(n int) []int {
	n = int(math.Pow(10, float64(n))) - 1
	ans := make([]int, n)
	for i := range ans {
		ans[i] = i + 1
	}
	return ans
}

func print(n int) []string {
	var dfs func(i, j int)
	s := []byte{}
	ans := []string{}
	dfs = func(i, j int) {
		if i == j {
			ans = append(ans, string(s))
			return
		}
		k := 0
		if i == 0 {
			k++
		}
		for k < 10 {
			s = append(s, byte('0'+k))
			dfs(i+1, j)
			s = s[:len(s)-1]
			k++
		}
	}
	for i := 1; i <= n; i++ {
		dfs(0, i)
	}
	return ans
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
