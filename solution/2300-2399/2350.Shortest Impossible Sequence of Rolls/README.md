# [2350. 不可能得到的最短骰子序列](https://leetcode.cn/problems/shortest-impossible-sequence-of-rolls)

[English Version](/solution/2300-2399/2350.Shortest%20Impossible%20Sequence%20of%20Rolls/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>rolls</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。你扔一个&nbsp;<code>k</code>&nbsp;面的骰子 <code>n</code>&nbsp;次，骰子的每个面分别是&nbsp;<code>1</code>&nbsp;到&nbsp;<code>k</code>&nbsp;，其中第&nbsp;<code>i</code>&nbsp;次扔得到的数字是&nbsp;<code>rolls[i]</code>&nbsp;。</p>

<p>请你返回 <strong>无法</strong>&nbsp;从 <code>rolls</code>&nbsp;中得到的 <strong>最短</strong>&nbsp;骰子子序列的长度。</p>

<p>扔一个 <code>k</code>&nbsp;面的骰子 <code>len</code>&nbsp;次得到的是一个长度为 <code>len</code>&nbsp;的 <strong>骰子子序列</strong>&nbsp;。</p>

<p><strong>注意</strong>&nbsp;，子序列只需要保持在原数组中的顺序，不需要连续。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>rolls = [4,2,1,2,3,3,2,4,1], k = 4
<b>输出：</b>3
<b>解释：</b>所有长度为 1 的骰子子序列 [1] ，[2] ，[3] ，[4] 都可以从原数组中得到。
所有长度为 2 的骰子子序列 [1, 1] ，[1, 2] ，... ，[4, 4] 都可以从原数组中得到。
子序列 [1, 4, 2] 无法从原数组中得到，所以我们返回 3 。
还有别的子序列也无法从原数组中得到。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>rolls = [1,1,2,2], k = 2
<b>输出：</b>2
<b>解释：</b>所有长度为 1 的子序列 [1] ，[2] 都可以从原数组中得到。
子序列 [2, 1] 无法从原数组中得到，所以我们返回 2 。
还有别的子序列也无法从原数组中得到，但 [2, 1] 是最短的子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>rolls = [1,1,3,2,2,2,3,3], k = 4
<b>输出：</b>1
<b>解释：</b>子序列 [4] 无法从原数组中得到，所以我们返回 1 。
还有别的子序列也无法从原数组中得到，但 [4] 是最短的子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rolls.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rolls[i] &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：脑筋急转弯**

时间复杂度 $O(n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shortestSequence(self, rolls: List[int], k: int) -> int:
        ans = 1
        s = set()
        for v in rolls:
            s.add(v)
            if len(s) == k:
                ans += 1
                s.clear()
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shortestSequence(int[] rolls, int k) {
        Set<Integer> s = new HashSet<>();
        int ans = 1;
        for (int v : rolls) {
            s.add(v);
            if (s.size() == k) {
                s.clear();
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestSequence(vector<int>& rolls, int k) {
        unordered_set<int> s;
        int ans = 1;
        for (int v : rolls) {
            s.insert(v);
            if (s.size() == k) {
                s.clear();
                ++ans;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestSequence(rolls []int, k int) int {
	s := map[int]bool{}
	ans := 1
	for _, v := range rolls {
		s[v] = true
		if len(s) == k {
			ans++
			s = map[int]bool{}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
