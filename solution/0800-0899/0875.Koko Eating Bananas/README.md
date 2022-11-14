# [875. 爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas)

[English Version](/solution/0800-0899/0875.Koko%20Eating%20Bananas/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>珂珂喜欢吃香蕉。这里有 <code>n</code> 堆香蕉，第 <code>i</code> 堆中有&nbsp;<code>piles[i]</code>&nbsp;根香蕉。警卫已经离开了，将在 <code>h</code> 小时后回来。</p>

<p>珂珂可以决定她吃香蕉的速度 <code>k</code> （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 <code>k</code> 根。如果这堆香蕉少于 <code>k</code> 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。&nbsp;&nbsp;</p>

<p>珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。</p>

<p>返回她可以在 <code>h</code> 小时内吃掉所有香蕉的最小速度 <code>k</code>（<code>k</code> 为整数）。</p>

<p>&nbsp;</p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [3,6,7,11], h = 8
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>piles = [30,11,23,4,20], h = 5
<strong>输出：</strong>30
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>piles = [30,11,23,4,20], h = 6
<strong>输出：</strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>piles.length &lt;= h &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

二分枚举速度值，找到能在 $h$ 小时内吃完所有香蕉的最小速度值。

时间复杂度 $O(n\log m)$，空间复杂度 $O(1)$。其中 $n$ 是 `piles` 的长度，而 $m$ 是 `piles` 中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        left, right = 1, int(1e9)
        while left < right:
            mid = (left + right) >> 1
            s = sum((x + mid - 1) // mid for x in piles)
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
        int left = 1, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int s = 0;
            for (int x : piles) {
                s += (x + mid - 1) / mid;
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
        int left = 1, right = 1e9;
        while (left < right) {
            int mid = (left + right) >> 1;
            int s = 0;
            for (int& x : piles) s += (x + mid - 1) / mid;
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
	return sort.Search(1e9, func(i int) bool {
		if i == 0 {
			return false
		}
		s := 0
		for _, x := range piles {
			s += (x + i - 1) / i
		}
		return s <= h
	})
}
```

### **TypeScript**

```ts
function minEatingSpeed(piles: number[], h: number): number {
    let left = 1;
    let right = Math.max(...piles);
    while (left < right) {
        const mid = (left + right) >> 1;
        let s = 0;
        for (const x of piles) {
            s += Math.ceil(x / mid);
        }
        if (s <= h) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
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
