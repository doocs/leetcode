# [842. 将数组拆分成斐波那契序列](https://leetcode.cn/problems/split-array-into-fibonacci-sequence)

[English Version](/solution/0800-0899/0842.Split%20Array%20into%20Fibonacci%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数字字符串 <code>num</code>，比如 <code>"123456579"</code>，我们可以将它分成「斐波那契式」的序列 <code>[123, 456, 579]</code>。</p>

<p>形式上，<strong>斐波那契式&nbsp;</strong>序列是一个非负整数列表 <code>f</code>，且满足：</p>

<ul>
	<li><code>0 &lt;= f[i] &lt; 2<sup>31</sup></code>&nbsp;，（也就是说，每个整数都符合 <strong>32 位</strong>&nbsp;有符号整数类型）</li>
	<li><code>f.length &gt;= 3</code></li>
	<li>对于所有的<code>0 &lt;= i &lt; f.length - 2</code>，都有 <code>f[i] + f[i + 1] = f[i + 2]</code></li>
</ul>

<p>另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 <code>0</code> 本身。</p>

<p>返回从 <code>num</code> 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 <code>[]</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = "1101111"
<strong>输出：</strong>[11,0,11,11]
<strong>解释：</strong>输出[110,1,111]也可以。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>num = "112358130"
<strong>输出: </strong>[]
<strong>解释: </strong>无法拆分。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>"0123"
<strong>输出：</strong>[]
<strong>解释：</strong>每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 200</code></li>
	<li><code>num</code>&nbsp;中只含有数字</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：回溯 + 剪枝**

我们设计一个函数 $dfs(i)$，表示从字符串 $num$ 的第 $i$ 个字符开始拆分，拆分出的斐波那契式序列是否满足题目要求。如果满足，我们就返回 $true$，否则返回 $false$。

函数 $dfs(i)$ 的具体实现如下：

如果 $i$ 等于字符串 $num$ 的长度，说明我们已经拆分完整个字符串，此时我们只需要判断拆分出的序列的长度是否大于 $2$ 即可。如果大于 $2$，说明我们找到了一组满足题目要求的斐波那契式序列，返回 $true$；否则返回 $false$。

如果 $i$ 小于字符串 $num$ 的长度，我们需要枚举拆分出的第一个数 $x$，如果 $x$ 的长度大于 $1$，且以 $0$ 开头，说明 $x$ 不是一个合法的数，我们直接返回 $false$。否则我们将 $x$ 转换成十进制数，如果 $x$ 大于 $2^{31} - 1$，或者 $x$ 大于 $ans$ 的最后两个数之和，直接返回 $false$。如果 $ans$ 的长度小于 $2$，或者 $x$ 等于 $ans$ 的最后两个数之和，我们将 $x$ 加入到 $ans$ 中，然后继续拆分字符串 $num$ 的后面的部分，如果返回 $true$，说明我们找到了一组满足题目要求的斐波那契式序列，返回 $true$；否则我们将 $x$ 从 $ans$ 中移除，然后继续枚举拆分出的第一个数 $x$。

时间复杂度 $O(n \times \log^2 M)$，空间复杂度 $O(n)$。其中 $n$ 和 $M$ 分别是字符串 $num$ 的长度和整型数的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def splitIntoFibonacci(self, num: str) -> List[int]:
        def dfs(i):
            if i == n:
                return len(ans) > 2
            x = 0
            for j in range(i, n):
                if j > i and num[i] == '0':
                    break
                x = x * 10 + int(num[j])
                if x > 2**31 - 1 or (len(ans) > 2 and x > ans[-2] + ans[-1]):
                    break
                if len(ans) < 2 or ans[-2] + ans[-1] == x:
                    ans.append(x)
                    if dfs(j + 1):
                        return True
                    ans.pop()
            return False

        n = len(num)
        ans = []
        dfs(0)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private List<Integer> ans = new ArrayList<>();
    private String num;

    public List<Integer> splitIntoFibonacci(String num) {
        this.num = num;
        dfs(0);
        return ans;
    }

    private boolean dfs(int i) {
        if (i == num.length()) {
            return ans.size() >= 3;
        }
        long x = 0;
        for (int j = i; j < num.length(); ++j) {
            if (j > i && num.charAt(i) == '0') {
                break;
            }
            x = x * 10 + num.charAt(j) - '0';
            if (x > Integer.MAX_VALUE
                || (ans.size() >= 2 && x > ans.get(ans.size() - 1) + ans.get(ans.size() - 2))) {
                break;
            }
            if (ans.size() < 2 || x == ans.get(ans.size() - 1) + ans.get(ans.size() - 2)) {
                ans.add((int) x);
                if (dfs(j + 1)) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> splitIntoFibonacci(string num) {
        int n = num.size();
        vector<int> ans;
        function<bool(int)> dfs = [&](int i) -> bool {
            if (i == n) {
                return ans.size() > 2;
            }
            long long x = 0;
            for (int j = i; j < n; ++j) {
                if (j > i && num[i] == '0') {
                    break;
                }
                x = x * 10 + num[j] - '0';
                if (x > INT_MAX || (ans.size() > 1 && x > (long long) ans[ans.size() - 1] + ans[ans.size() - 2])) {
                    break;
                }
                if (ans.size() < 2 || x == (long long) ans[ans.size() - 1] + ans[ans.size() - 2]) {
                    ans.push_back(x);
                    if (dfs(j + 1)) {
                        return true;
                    }
                    ans.pop_back();
                }
            }
            return false;
        };
        dfs(0);
        return ans;
    }
};
```

### **Go**

```go
func splitIntoFibonacci(num string) []int {
	n := len(num)
	ans := []int{}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i == n {
			return len(ans) > 2
		}
		x := 0
		for j := i; j < n; j++ {
			if j > i && num[i] == '0' {
				break
			}
			x = x*10 + int(num[j]-'0')
			if x > math.MaxInt32 || (len(ans) > 1 && x > ans[len(ans)-1]+ans[len(ans)-2]) {
				break
			}
			if len(ans) < 2 || x == ans[len(ans)-1]+ans[len(ans)-2] {
				ans = append(ans, x)
				if dfs(j + 1) {
					return true
				}
				ans = ans[:len(ans)-1]
			}
		}
		return false
	}
	dfs(0)
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
