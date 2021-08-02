# [370. 区间加法](https://leetcode-cn.com/problems/range-addition)

[English Version](/solution/0300-0399/0370.Range%20Addition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你有一个长度为&nbsp;<em><strong>n</strong></em>&nbsp;的数组，初始情况下所有的数字均为&nbsp;<strong>0</strong>，你将会被给出&nbsp;<em><strong>k</strong></em>​​​​​​<em>​</em> 个更新的操作。</p>

<p>其中，每个操作会被表示为一个三元组：<strong>[startIndex, endIndex, inc]</strong>，你需要将子数组&nbsp;<strong>A[startIndex ... endIndex]</strong>（包括 startIndex 和 endIndex）增加&nbsp;<strong>inc</strong>。</p>

<p>请你返回&nbsp;<strong><em>k</em></strong>&nbsp;次操作后的数组。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入: </strong>length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
<strong>输出: </strong>[-2,0,3,5,3]
</pre>

<p><strong>解释:</strong></p>

<pre>初始状态:
[0,0,0,0,0]

进行了操作 [1,3,2] 后的状态:
[0,2,2,2,0]

进行了操作 [2,4,3] 后的状态:
[0,2,5,5,3]

进行了操作 [0,2,-2] 后的状态:
[-2,0,3,5,3]
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

差分数组。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        delta = [0] * length
        for start, end, inc in updates:
            delta[start] += inc
            if end + 1 < length:
                delta[end + 1] -= inc
        for i in range(1, length):
            delta[i] += delta[i - 1]
        return delta
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] delta = new int[length];
        for (int[] e : updates) {
            delta[e[0]] += e[2];
            if (e[1] + 1 < length) {
                delta[e[1] + 1] -= e[2];
            }
        }
        for (int i = 1; i < length; ++i) {
            delta[i] += delta[i - 1];
        }
        return delta;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> delta(length);
        for (auto e : updates) {
            delta[e[0]] += e[2];
            if (e[1] + 1 < length) delta[e[1] + 1] -= e[2];
        }
        for (int i = 1; i < length; ++i) delta[i] += delta[i - 1];
        return delta;
    }
};
```

### **Go**

```go
func getModifiedArray(length int, updates [][]int) []int {
	delta := make([]int, length)
	for _, e := range updates {
		delta[e[0]] += e[2]
		if e[1]+1 < length {
			delta[e[1]+1] -= e[2]
		}
	}
	for i := 1; i < length; i++ {
		delta[i] += delta[i-1]
	}
	return delta
}
```

### **...**

```

```

<!-- tabs:end -->
