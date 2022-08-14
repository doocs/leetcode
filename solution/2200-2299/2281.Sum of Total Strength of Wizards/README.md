# [2281. 巫师的总力量和](https://leetcode.cn/problems/sum-of-total-strength-of-wizards)

[English Version](/solution/2200-2299/2281.Sum%20of%20Total%20Strength%20of%20Wizards/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>作为国王的统治者，你有一支巫师军队听你指挥。</p>

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>strength</code>&nbsp;，其中&nbsp;<code>strength[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;位巫师的力量值。对于连续的一组巫师（也就是这些巫师的力量值是&nbsp;<code>strength</code>&nbsp;的&nbsp;<strong>子数组</strong>），<strong>总力量</strong>&nbsp;定义为以下两个值的&nbsp;<strong>乘积</strong>&nbsp;：</p>

<ul>
	<li>巫师中 <strong>最弱</strong>&nbsp;的能力值。</li>
	<li>组中所有巫师的个人力量值 <strong>之和</strong>&nbsp;。</li>
</ul>

<p>请你返回 <strong>所有</strong>&nbsp;巫师组的 <strong>总</strong>&nbsp;力量之和。由于答案可能很大，请将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p><strong>子数组</strong>&nbsp;是一个数组里 <strong>非空</strong>&nbsp;连续子序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>strength = [1,3,1,2]
<b>输出：</b>44
<b>解释：</b>以下是所有连续巫师组：
- [<em><strong>1</strong></em>,3,1,2] 中 [1] ，总力量值为 min([1]) * sum([1]) = 1 * 1 = 1
- [1,<em><strong>3</strong></em>,1,2] 中 [3] ，总力量值为 min([3]) * sum([3]) = 3 * 3 = 9
- [1,3,<em><strong>1</strong></em>,2] 中 [1] ，总力量值为 min([1]) * sum([1]) = 1 * 1 = 1
- [1,3,1,<em><strong>2</strong></em>] 中 [2] ，总力量值为 min([2]) * sum([2]) = 2 * 2 = 4
- [<em><strong>1,3</strong></em>,1,2] 中 [1,3] ，总力量值为 min([1,3]) * sum([1,3]) = 1 * 4 = 4
- [1,<em><strong>3,1</strong></em>,2] 中 [3,1] ，总力量值为 min([3,1]) * sum([3,1]) = 1 * 4 = 4
- [1,3,<em><strong>1,2</strong></em>] 中 [1,2] ，总力量值为 min([1,2]) * sum([1,2]) = 1 * 3 = 3
- [<em><strong>1,3,1</strong></em>,2] 中 [1,3,1] ，总力量值为 min([1,3,1]) * sum([1,3,1]) = 1 * 5 = 5
- [1,<em><strong>3,1,2</strong></em>] 中 [3,1,2] ，总力量值为 min([3,1,2]) * sum([3,1,2]) = 1 * 6 = 6
- [<em><strong>1,3,1,2</strong></em>] 中 [1,3,1,2] ，总力量值为 min([1,3,1,2]) * sum([1,3,1,2]) = 1 * 7 = 7
所有力量值之和为 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7 = 44 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>strength = [5,4,6]
<b>输出：</b>213
<b>解释：</b>以下是所有连续巫师组：
- [<em><strong>5</strong></em>,4,6] 中 [5] ，总力量值为 min([5]) * sum([5]) = 5 * 5 = 25
- [5,<em><strong>4</strong></em>,6] 中 [4] ，总力量值为 min([4]) * sum([4]) = 4 * 4 = 16
- [5,4,<em><strong>6</strong></em>] 中 [6] ，总力量值为 min([6]) * sum([6]) = 6 * 6 = 36
- [<em><strong>5,4</strong></em>,6] 中 [5,4] ，总力量值为 min([5,4]) * sum([5,4]) = 4 * 9 = 36
- [5,<em><strong>4,6</strong></em>] 中 [4,6] ，总力量值为 min([4,6]) * sum([4,6]) = 4 * 10 = 40
- [<em><strong>5,4,6</strong></em>] 中 [5,4,6] ，总力量值为 min([5,4,6]) * sum([5,4,6]) = 4 * 15 = 60
所有力量值之和为 25 + 16 + 36 + 36 + 40 + 60 = 213 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strength.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= strength[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈 + 前缀和**

相似题目：[907. 子数组的最小值之和](/solution/0900-0999/0907.Sum%20of%20Subarray%20Minimums/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def totalStrength(self, strength: List[int]) -> int:
        n = len(strength)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(strength):
            while stk and strength[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and strength[stk[-1]] > strength[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)

        ss = list(accumulate(list(accumulate(strength, initial=0)), initial=0))
        mod = int(1e9) + 7
        ans = 0
        for i, v in enumerate(strength):
            l, r = left[i] + 1, right[i] - 1
            a = (ss[r + 2] - ss[i + 1]) * (i - l + 1)
            b = (ss[i + 1] - ss[l]) * (r - i + 1)
            ans = (ans + (a - b) * v) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int totalStrength(int[] strength) {
        int n = strength.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && strength[stk.peek()] >= strength[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && strength[stk.peek()] > strength[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int mod = (int) 1e9 + 7;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = (s[i] + strength[i]) % mod;
        }
        int[] ss = new int[n + 2];
        for (int i = 0; i < n + 1; ++i) {
            ss[i + 1] = (ss[i] + s[i]) % mod;
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = strength[i];
            int l = left[i] + 1, r = right[i] - 1;
            long a = (long) (i - l + 1) * (ss[r + 2] - ss[i + 1]);
            long b = (long) (r - i + 1) * (ss[i + 1] - ss[l]);
            ans = (ans + v * ((a - b) % mod)) % mod;
        }
        return (int) (ans + mod) % mod;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int totalStrength(vector<int>& strength) {
        int n = strength.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && strength[stk.top()] >= strength[i]) stk.pop();
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty() && strength[stk.top()] > strength[i]) stk.pop();
            if (!stk.empty()) right[i] = stk.top();
            stk.push(i);
        }
        int mod = 1e9 + 7;
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = (s[i] + strength[i]) % mod;
        vector<int> ss(n + 2);
        for (int i = 0; i < n + 1; ++i) ss[i + 1] = (ss[i] + s[i]) % mod;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = strength[i];
            int l = left[i] + 1, r = right[i] - 1;
            long a = (long)(i - l + 1) * (ss[r + 2] - ss[i + 1]);
            long b = (long)(r - i + 1) * (ss[i + 1] - ss[l]);
            ans = (ans + v * ((a - b) % mod)) % mod;
        }
        return (int)(ans + mod) % mod;
    }
};
```

### **Go**

```go
func totalStrength(strength []int) int {
	n := len(strength)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range strength {
		for len(stk) > 0 && strength[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && strength[stk[len(stk)-1]] > strength[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	mod := int(1e9) + 7
	s := make([]int, n+1)
	for i, v := range strength {
		s[i+1] = (s[i] + v) % mod
	}
	ss := make([]int, n+2)
	for i, v := range s {
		ss[i+1] = (ss[i] + v) % mod
	}
	ans := 0
	for i, v := range strength {
		l, r := left[i]+1, right[i]-1
		a := (ss[r+2] - ss[i+1]) * (i - l + 1)
		b := (ss[i+1] - ss[l]) * (r - i + 1)
		ans = (ans + v*((a-b)%mod)) % mod
	}
	return (ans + mod) % mod
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
