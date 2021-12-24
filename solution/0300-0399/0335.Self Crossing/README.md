# [335. 路径交叉](https://leetcode-cn.com/problems/self-crossing)

[English Version](/solution/0300-0399/0335.Self%20Crossing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个含有&nbsp;<code>n</code>&nbsp;个正数的数组&nbsp;<em>x</em>。从点&nbsp;<code>(0,0)</code>&nbsp;开始，先向北移动&nbsp;<code>x[0]</code>&nbsp;米，然后向西移动&nbsp;<code>x[1]</code>&nbsp;米，向南移动&nbsp;<code>x[2]</code>&nbsp;米，向东移动&nbsp;<code>x[3]</code>&nbsp;米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。</p>

<p>编写一个&nbsp;<code>O(1)</code>&nbsp;空间复杂度的一趟扫描算法，判断你所经过的路径是否相交。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre><strong>┌───┐
│ &nbsp; │
└───┼──&gt;
&nbsp; &nbsp; │

输入: </strong><code>[2,1,1,2]</code>
<strong>输出:</strong> true 
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>┌──────┐
│ &nbsp; &nbsp; &nbsp;│
│
│
└────────────&gt;

输入: </strong><code>[1,2,3,4]</code>
<strong>输出: </strong>false 
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>┌───┐
│ &nbsp; │
└───┼&gt;

输入:</strong> <code>[1,1,1,1]</code>
<strong>输出:</strong> true 
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

```bash
                i-2
    case 1 : i-1┌─┐
                └─┼─>i
                 i-3

                   i-2
    case 2 : i-1 ┌────┐
                 └─══>┘i-3
                 i  i-4

    case 3 :    i-4
               ┌──┐
               │i<┼─┐
            i-3│ i-5│i-1
               └────┘
                i-2
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isSelfCrossing(self, distance: List[int]) -> bool:
        d = distance
        for i in range(3, len(d)):
            if d[i] >= d[i - 2] and d[i - 1] <= d[i - 3]:
                return True
            if i >= 4 and d[i - 1] == d[i - 3] and d[i] + d[i - 4] >= d[i - 2]:
                return True
            if i >= 5 and d[i - 2] >= d[i - 4] and d[i - 1] <= d[i - 3] and d[i] >= d[i - 2] - d[i - 4] and d[i - 1] + d[i - 5] >= d[i - 3]:
                return True
        return False
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isSelfCrossing(int[] distance) {
        int[] d = distance;
        for (int i = 3; i < d.length; ++i) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) {
                return true;
            }
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) {
                return true;
            }
            if (i >= 5 && d[i - 2] >= d[i - 4] && d[i - 1] <= d[i - 3] && d[i] >= d[i - 2] - d[i - 4] && d[i - 1] + d[i - 5] >= d[i - 3]) {
                return true;
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
    bool isSelfCrossing(vector<int>& distance) {
        vector<int> d = distance;
        for (int i = 3; i < d.size(); ++i)
        {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) return true;
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) return true;
            if (i >= 5 && d[i - 2] >= d[i - 4] && d[i - 1] <= d[i - 3] && d[i] >= d[i - 2] - d[i - 4] && d[i - 1] + d[i - 5] >= d[i - 3]) return true;
        }
        return false;
    }
};
```

### **Go**

```go
func isSelfCrossing(distance []int) bool {
	d := distance
	for i := 3; i < len(d); i++ {
		if d[i] >= d[i-2] && d[i-1] <= d[i-3] {
			return true
		}
		if i >= 4 && d[i-1] == d[i-3] && d[i]+d[i-4] >= d[i-2] {
			return true
		}
		if i >= 5 && d[i-2] >= d[i-4] && d[i-1] <= d[i-3] && d[i] >= d[i-2]-d[i-4] && d[i-1]+d[i-5] >= d[i-3] {
			return true
		}
	}
	return false
}
```

### **...**

```

```

<!-- tabs:end -->
