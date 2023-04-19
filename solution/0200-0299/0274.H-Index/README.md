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

**方法一：排序**

我们可以先对数组 `citations` 按照元素值从大到小进行排序。然后我们从大到小枚举 $h$ 值，如果某个 $h$ 值满足 $citations[h-1] \geq h$，则说明有至少 $h$ 篇论文分别被引用了至少 $h$ 次，直接返回 $h$ 即可。如果没有找到这样的 $h$ 值，说明所有的论文都没有被引用，返回 $0$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 `citations` 的长度。

**方法二：计数 + 求和**

我们可以使用一个长度为 $n+1$ 的数组 $cnt$，其中 $cnt[i]$ 表示引用次数为 $i$ 的论文的篇数。我们遍历数组 `citations`，将引用次数大于 $n$ 的论文都当作引用次数为 $n$ 的论文，然后将每篇论文的引用次数作为下标，将 $cnt$ 中对应的元素值加 $1$。这样我们就统计出了每个引用次数对应的论文篇数。

接下来，我们从大到小枚举 $h$ 值，将 $cnt$ 中下标为 $h$ 的元素值加到变量 $s$ 中，其中 $s$ 表示引用次数大于等于 $h$ 的论文篇数。如果 $s \geq h$，说明至少有 $h$ 篇论文分别被引用了至少 $h$ 次，直接返回 $h$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `citations` 的长度。

**方法三：二分查找**

我们注意到，如果存在一个 $h$ 值满足至少有 $h$ 篇论文至少被引用 $h$ 次，那么对于任意一个 $h' \lt h$，都有至少 $h'$ 篇论文至少被引用 $h'$ 次。因此我们可以使用二分查找的方法，找到最大的 $h$ 值，使得至少有 $h$ 篇论文至少被引用 $h$ 次。

我们定义二分查找的左边界 $l=0$，右边界 $r=n$。每次我们取 $mid = \lfloor \frac{l + r + 1}{2} \rfloor$，其中 $\lfloor x \rfloor$ 表示对 $x$ 向下取整。然后我们统计数组 `citations` 中大于等于 $mid$ 的元素的个数，记为 $s$。如果 $s \geq mid$，说明至少有 $mid$ 篇论文至少被引用 $mid$ 次，此时我们将左边界 $l$ 变为 $mid$，否则我们将右边界 $r$ 变为 $mid-1$。当左边界 $l$ 等于右边界 $r$ 时，我们找到了最大的 $h$ 值，即为 $l$ 或 $r$。

时间复杂度 $O(n \times \log n)$，其中 $n$ 是数组 `citations` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        citations.sort(reverse=True)
        for h in range(len(citations), 0, -1):
            if citations[h - 1] >= h:
                return h
        return 0
```

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        cnt = [0] * (n + 1)
        for x in citations:
            cnt[min(x, n)] += 1
        s = 0
        for h in range(n, -1, -1):
            s += cnt[h]
            if s >= h:
                return h
```

```python
class Solution:
    def hIndex(self, citations: List[int]) -> int:
        l, r = 0, len(citations)
        while l < r:
            mid = (l + r + 1) >> 1
            if sum(x >= mid for x in citations) >= mid:
                l = mid
            else:
                r = mid - 1
        return l
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int h = n; h > 0; --h) {
            if (citations[n - h] >= h) {
                return h;
            }
        }
        return 0;
    }
}
```

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        for (int x : citations) {
            ++cnt[Math.min(x, n)];
        }
        for (int h = n, s = 0; ; --h) {
            s += cnt[h];
            if (s >= h) {
                return h;
            }
        }
    }
}
```

```java
class Solution {
    public int hIndex(int[] citations) {
        int l = 0, r = citations.length;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int s = 0;
            for (int x : citations) {
                if (x >= mid) {
                    ++s;
                }
            }
            if (s >= mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        sort(citations.rbegin(), citations.rend());
        for (int h = citations.size(); h; --h) {
            if (citations[h - 1] >= h) {
                return h;
            }
        }
        return 0;
    }
};
```

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int n = citations.size();
        int cnt[n + 1];
        memset(cnt, 0, sizeof(cnt));
        for (int x : citations) {
            ++cnt[min(x, n)];
        }
        for (int h = n, s = 0;; --h) {
            s += cnt[h];
            if (s >= h) {
                return h;
            }
        }
    }
};
```

```cpp
class Solution {
public:
    int hIndex(vector<int>& citations) {
        int l = 0, r = citations.size();
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int s = 0;
            for (int x : citations) {
                if (x >= mid) {
                    ++s;
                }
            }
            if (s >= mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

### **Go**

```go
func hIndex(citations []int) int {
	sort.Ints(citations)
	n := len(citations)
	for h := n; h > 0; h-- {
		if citations[n-h] >= h {
			return h
		}
	}
	return 0
}
```

```go
func hIndex(citations []int) int {
	n := len(citations)
	cnt := make([]int, n+1)
	for _, x := range citations {
		cnt[min(x, n)]++
	}
	for h, s := n, 0; ; h-- {
		s += cnt[h]
		if s >= h {
			return h
		}
	}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func hIndex(citations []int) int {
	l, r := 0, len(citations)
	for l < r {
		mid := (l + r + 1) >> 1
		s := 0
		for _, x := range citations {
			if x >= mid {
				s++
			}
		}
		if s >= mid {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
```

### **TypeScript**

```ts
function hIndex(citations: number[]): number {
    citations.sort((a, b) => b - a);
    for (let h = citations.length; h; --h) {
        if (citations[h - 1] >= h) {
            return h;
        }
    }
    return 0;
}
```

```ts
function hIndex(citations: number[]): number {
    const n: number = citations.length;
    const cnt: number[] = new Array(n + 1).fill(0);
    for (const x of citations) {
        ++cnt[Math.min(x, n)];
    }
    for (let h = n, s = 0; ; --h) {
        s += cnt[h];
        if (s >= h) {
            return h;
        }
    }
}
```

```ts
function hIndex(citations: number[]): number {
    let l = 0;
    let r = citations.length;
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        let s = 0;
        for (const x of citations) {
            if (x >= mid) {
                ++s;
            }
        }
        if (s >= mid) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

### **...**

```

```

<!-- tabs:end -->
