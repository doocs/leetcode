# [274. H 指数](https://leetcode.cn/problems/h-index)

[English Version](/solution/0200-0299/0274.H-Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>citations</code> ，其中 <code>citations[i]</code> 表示研究者的第 <code>i</code> 篇论文被引用的次数。计算并返回该研究者的 <strong><code>h</code><em>&nbsp;</em>指数</strong>。</p>

<p>根据维基百科上&nbsp;<a href="https://baike.baidu.com/item/h-index/3991452?fr=aladdin" target="_blank">h 指数的定义</a>：h 代表“高引用次数”，一名科研人员的 <code>h</code><strong>指数</strong>是指他（她）的 （<code>n</code> 篇论文中）<strong>总共</strong>有 <code>h</code> 篇论文分别被引用了<strong>至少</strong> <code>h</code> 次。且其余的 <em><code>n - h</code>&nbsp;</em>篇论文每篇被引用次数&nbsp;<strong>不超过 </strong><em><code>h</code> </em>次。</p>

<p>如果 <code>h</code><em> </em>有多种可能的值，<strong><code>h</code> 指数 </strong>是其中最大的那个。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>citations = [3,0,6,1,5]</code>
<strong>输出：</strong>3 
<strong>解释：</strong>给定数组表示研究者总共有 <code>5</code> 篇论文，每篇论文相应的被引用了 <code>3, 0, 6, 1, 5</code> 次。
&nbsp;    由于研究者有 <code>3 </code>篇论文每篇 <strong>至少 </strong>被引用了 <code>3</code> 次，其余两篇论文每篇被引用 <strong>不多于</strong> <code>3</code> 次，所以她的 <em>h </em>指数是 <code>3</code>。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>citations = [1,3,1]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == citations.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= citations[i] &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

最简单的解法就是排序之后再判断，但是因为 `H` 不可能大于论文的总数 `n`，所以可以用计数排序进行优化。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        cnt = [0] * (n + 1)
        for c in citations:
            if c <= n:
                cnt[c] += 1
            else:
                cnt[n] += 1
        sum = 0
        for i in range(n, -1, -1):
            sum += cnt[i]
            if sum >= i:
                return i
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        for (int c : citations) {
            if (c <= n) {
                ++cnt[c];
            } else {
                ++cnt[n];
            }
        }
        int sum = 0;
        for (int i = n; i >= 0; --i) {
            sum += cnt[i];
            if (sum >= i) {
                return i;
            }
        }
        return 0;
    }
}
```

### **TypeScript**

```ts
function hIndex(citations: number[]): number {
    let n = citations.length;
    let cnt = new Array(n + 1).fill(0);
    for (let c of citations) {
        if (c <= n) {
            ++cnt[c];
        } else {
            ++cnt[n];
        }
    }
    let sum = 0;
    for (let i = n; i > -1; --i) {
        sum += cnt[i];
        if (sum >= i) {
            return i;
        }
    }
    return 0;
}
```

### **Go**

利用二分查找，定位符合条件的最大值

```go
func hIndex(citations []int) int {
	n := len(citations)
	left, right := 0, n
	for left+1 < right {
		mid := int(uint(left+right) >> 1)
		if check(citations, mid) {
			left = mid
		} else {
			right = mid
		}
	}
	if check(citations, right) {
		return right
	}
	return left
}

func check(citations []int, mid int) bool {
	cnt := 0
	for _, citation := range citations {
		if citation >= mid {
			cnt++
		}
	}
	return cnt >= mid
}
```

### **...**

```

```

<!-- tabs:end -->
