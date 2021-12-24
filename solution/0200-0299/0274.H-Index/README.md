# [274. H 指数](https://leetcode-cn.com/problems/h-index)

[English Version](/solution/0200-0299/0274.H-Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 <em>h </em>指数。</p>

<p><a href="https://baike.baidu.com/item/h-index/3991452?fr=aladdin" target="_blank">h 指数的定义</a>：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）<strong>总共</strong>有 h 篇论文分别被引用了<strong>至少</strong> h 次。且其余的 <em>N - h </em>篇论文每篇被引用次数 <strong>不超过 </strong><em>h </em>次。</p>

<p>例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong><code>citations = [3,0,6,1,5]</code>
<strong>输出：</strong>3
<strong>解释：</strong>给定数组表示研究者总共有 <code>5</code> 篇论文，每篇论文相应的被引用了 <code>3, 0, 6, 1, 5</code> 次。
     由于研究者有 <code>3 </code>篇论文每篇 <strong>至少 </strong>被引用了 <code>3</code> 次，其余两篇论文每篇被引用 <strong>不多于</strong> <code>3</code> 次，所以她的 <em>h </em>指数是 <code>3</code>。</pre>

<p> </p>

<p><strong>提示：</strong>如果 <em>h </em>有多种可能的值，<em>h</em> 指数是其中最大的那个。</p>

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
        cnt = [0 for i in range(n + 1)]
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
