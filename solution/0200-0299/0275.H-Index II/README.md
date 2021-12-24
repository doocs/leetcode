# [275. H 指数 II](https://leetcode-cn.com/problems/h-index-ii)

[English Version](/solution/0200-0299/0275.H-Index%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照&nbsp;<strong>升序排列&nbsp;</strong>。编写一个方法，计算出研究者的 <em>h</em> 指数。</p>

<p><a href="https://baike.baidu.com/item/h-index/3991452?fr=aladdin">h 指数的定义</a>: &ldquo;h 代表&ldquo;高引用次数&rdquo;（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）<strong>总共</strong>有 h 篇论文分别被引用了<strong>至少</strong> h 次。（其余的&nbsp;<em>N - h&nbsp;</em>篇论文每篇被引用次数<strong>不多于 </strong><em>h </em>次。）&quot;</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <code>citations = [0,1,3,5,6]</code>
<strong>输出:</strong> 3 
<strong>解释: </strong>给定数组表示研究者总共有 <code>5</code> 篇论文，每篇论文相应的被引用了 0<code>, 1, 3, 5, 6</code> 次。
&nbsp;    由于研究者有 <code>3 </code>篇论文每篇<strong>至少</strong>被引用了 <code>3</code> 次，其余两篇论文每篇被引用<strong>不多于</strong> <code>3</code> 次，所以她的<em> h </em>指数是 <code>3</code>。</pre>

<p>&nbsp;</p>

<p><strong>说明:</strong></p>

<p>如果 <em>h </em>有多有种可能的值 ，<em>h</em> 指数是其中最大的那个。</p>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>这是&nbsp;<a href="/problems/h-index/description/">H 指数</a>&nbsp;的延伸题目，本题中的&nbsp;<code>citations</code>&nbsp;数组是保证有序的。</li>
	<li>你可以优化你的算法到对数时间复杂度吗？</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二分查找。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        left, right = 0, n
        while left < right:
            mid = (left + right) >> 1
            if citations[mid] >= n - mid:
                right = mid
            else:
                left = mid + 1
        return n - left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (citations[mid] >= n - mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int n = citations.size();
        int left = 0, right = n;
        while (left < right) {
            int mid = left + right >> 1;
            if (citations[mid] >= n - mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }
};
```

### **Go**

```go
func hIndex(citations []int) int {
	n := len(citations)
	left, right := 0, n
	for left < right {
		mid := (left + right) >> 1
		if citations[mid] >= n-mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return n - left
}
```

### **...**

```

```

<!-- tabs:end -->
