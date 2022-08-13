# [剑指 Offer II 073. 狒狒吃香蕉](https://leetcode.cn/problems/nZZqjQ)

## 题目描述

<!-- 这里写题目描述 -->

<p>狒狒喜欢吃香蕉。这里有&nbsp;<code>N</code>&nbsp;堆香蕉，第 <code>i</code> 堆中有&nbsp;<code>piles[i]</code>&nbsp;根香蕉。警卫已经离开了，将在&nbsp;<code>H</code>&nbsp;小时后回来。</p>

<p>狒狒可以决定她吃香蕉的速度&nbsp;<code>K</code>&nbsp;（单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 <code>K</code> 根。如果这堆香蕉少于 <code>K</code> 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。&nbsp;&nbsp;</p>

<p>狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。</p>

<p>返回她可以在 <code>H</code> 小时内吃掉所有香蕉的最小速度 <code>K</code>（<code>K</code> 为整数）。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>piles = [3,6,7,11], H = 8
<strong>输出: </strong>4
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入: </strong>piles = [30,11,23,4,20], H = 5
<strong>输出: </strong>30
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre>
<strong>输入: </strong>piles = [30,11,23,4,20], H = 6
<strong>输出: </strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10^4</code></li>
	<li><code>piles.length &lt;= H &lt;= 10^9</code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10^9</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 875&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/koko-eating-bananas/">https://leetcode.cn/problems/koko-eating-bananas/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        left, right = 1, max(piles)
        while left < right:
            mid = (left + right) >> 1
            s = sum((pile + mid - 1) // mid for pile in piles)
            if s <= h:
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int mx = 0;
        for (int pile : piles) {
            mx = Math.max(mx, pile);
        }
        int left = 1, right = mx;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int s = 0;
            for (int pile : piles) {
                s += (pile + mid - 1) / mid;
            }
            if (s <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        int left = 1, right = *max_element(piles.begin(), piles.end());
        while (left < right) {
            int mid = left + right >> 1;
            int s = 0;
            for (int pile : piles) s += (pile + mid - 1) / mid;
            if (s <= h)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};
```

### **Go**

```go
func minEatingSpeed(piles []int, h int) int {
	mx := 0
	for _, pile := range piles {
		mx = max(mx, pile)
	}
	left, right := 1, mx
	for left < right {
		mid := (left + right) >> 1
		s := 0
		for _, pile := range piles {
			s += (pile + mid - 1) / mid
		}
		if s <= h {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int MinEatingSpeed(int[] piles, int h) {
        int left = 1, right = piles.Max();
        while (left < right)
        {
            int mid = (left + right) >> 1;
            int s = 0;
            foreach (int pile in piles)
            {
                s += (pile + mid - 1) / mid;
            }
            if (s <= h)
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
