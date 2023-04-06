# [LCP 02. 分式化简](https://leetcode.cn/problems/deep-dark-fraction)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2002.%20分式化简/images/fraction_example_1.jpg" style="height: 195px; width: 480px;" /></p>

<p>连分数是形如上图的分式。在本题中，所有系数都是大于等于0的整数。</p>

<p> </p>

<p>输入的<code>cont</code>代表连分数的系数（<code>cont[0]</code>代表上图的<code>a<sub>0</sub></code>，以此类推）。返回一个长度为2的数组<code>[n, m]</code>，使得连分数的值等于<code>n / m</code>，且<code>n, m</code>最大公约数为1。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>cont = [3, 2, 0, 2]
<strong>输出：</strong>[13, 4]
<strong>解释：</strong>原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8], [-13, -4]都不是正确答案。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>cont = [0, 0, 3]
<strong>输出：</strong>[3, 1]
<strong>解释：</strong>如果答案是整数，令分母为1即可。</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ol>
	<li><code>cont[i] >= 0</code></li>
	<li><code>1 <= cont的长度 <= 10</code></li>
	<li><code>cont</code>最后一个元素不等于0</li>
	<li>答案的<code>n, m</code>的取值都能被32位int整型存下（即不超过<code>2 ^ 31 - 1</code>）。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def fraction(self, cont: List[int]) -> List[int]:
        def dfs(cont):
            if len(cont) == 1:
                return [cont[0], 1]
            a, b = dfs(cont[1:])
            return [a * cont[0] + b, a]

        return dfs(cont)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] fraction(int[] cont) {
        return dfs(cont, 0);
    }

    private int[] dfs(int[] cont, int i) {
        if (i == cont.length - 1) {
            return new int[] {cont[i], 1};
        }
        int[] ans = dfs(cont, i + 1);
        int a = ans[0], b = ans[1];
        return new int[] {a * cont[i] + b, a};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> fraction(vector<int>& cont) {
        return dfs(cont, 0);
    }

    vector<int> dfs(vector<int>& cont, int i) {
        if (i == cont.size() - 1) return {cont[i], 1};
        vector<int> ans = dfs(cont, i + 1);
        int a = ans[0], b = ans[1];
        return {a * cont[i] + b, a};
    }
};
```

### **Go**

```go
func fraction(cont []int) []int {
	var dfs func(i int) []int
	dfs = func(i int) []int {
		if i == len(cont)-1 {
			return []int{cont[i], 1}
		}
		ans := dfs(i + 1)
		a, b := ans[0], ans[1]
		return []int{a*cont[i] + b, a}
	}
	return dfs(0)
}
```

### **JavaScript**

```js
/**
 * @param {number[]} cont
 * @return {number[]}
 */
var fraction = function (cont) {
    function dfs(i) {
        if (i === cont.length - 1) {
            return [cont[i], 1];
        }
        const [a, b] = dfs(i + 1);
        return [a * cont[i] + b, a];
    }
    return dfs(0);
};
```

### **...**

```

```

<!-- tabs:end -->
