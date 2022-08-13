# [898. 子数组按位或操作](https://leetcode.cn/problems/bitwise-ors-of-subarrays)

[English Version](/solution/0800-0899/0898.Bitwise%20ORs%20of%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有一个非负整数数组<meta charset="UTF-8" />&nbsp;<code>arr</code>&nbsp;。</p>

<p>对于每个（连续的）子数组<meta charset="UTF-8" />&nbsp;<code>sub = [arr[i], arr[i + 1], ..., arr[j]]</code>&nbsp;（&nbsp;<code>i &lt;= j</code>），我们对<meta charset="UTF-8" />&nbsp;<code>sub</code>&nbsp;中的每个元素进行按位或操作，获得结果<meta charset="UTF-8" />&nbsp;<code>arr[i] | arr[i + 1] | ... | arr[j]</code>&nbsp;。</p>

<p>返回可能结果的数量。 多次出现的结果在最终答案中仅计算一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0]
<strong>输出：</strong>1
<strong>解释：</strong>
只有一个可能的结果 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,2]
<strong>输出：</strong>3
<strong>解释：</strong>
可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
产生的结果为 1，1，2，1，3，3 。
有三个唯一值，所以答案是 3 。
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,4]
<strong>输出：</strong>6
<strong>解释：</strong>
可能的结果是 1，2，3，4，6，以及 7 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        s = set()
        prev = 0
        for i, v in enumerate(arr):
            prev |= v
            curr = 0
            for j in range(i, -1, -1):
                curr |= arr[j]
                s.add(curr)
                if curr == prev:
                    break
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> s = new HashSet<>();
        int prev = 0;
        for (int i = 0; i < arr.length; ++i) {
            prev |= arr[i];
            int curr = 0;
            for (int j = i; j >= 0; --j) {
                curr |= arr[j];
                s.add(curr);
                if (curr == prev) {
                    break;
                }
            }
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int subarrayBitwiseORs(vector<int>& arr) {
        unordered_set<int> s;
        int prev = 0;
        for (int i = 0; i < arr.size(); ++i) {
            prev |= arr[i];
            int curr = 0;
            for (int j = i; ~j; --j) {
                curr |= arr[j];
                s.insert(curr);
                if (curr == prev) break;
            }
        }
        return s.size();
    }
};
```

### **Go**

```go
func subarrayBitwiseORs(arr []int) int {
	s := map[int]bool{}
	prev := 0
	for i, v := range arr {
		prev |= v
		curr := 0
		for j := i; j >= 0; j-- {
			curr |= arr[j]
			s[curr] = true
			if curr == prev {
				break
			}
		}
	}
	return len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
