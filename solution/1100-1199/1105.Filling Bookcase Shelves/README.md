# [1105. 填充书架](https://leetcode.cn/problems/filling-bookcase-shelves)

[English Version](/solution/1100-1199/1105.Filling%20Bookcase%20Shelves/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>books</code> ，其中&nbsp;<code>books[i] = [thickness<sub>i</sub>, height<sub>i</sub>]</code>&nbsp;表示第 <code>i</code> 本书的厚度和高度。你也会得到一个整数 <code>shelfWidth</code> 。</p>

<p><strong>按顺序</strong>&nbsp;将这些书摆放到总宽度为 <code>shelfWidth</code> 的书架上。</p>

<p>先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 <code>shelfWidth</code> ），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。</p>

<p>需要注意的是，在上述过程的每个步骤中，<strong>摆放书的顺序与你整理好的顺序相同</strong>。</p>

<ul>
	<li>例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。</li>
</ul>

<p>每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。</p>

<p>以这种方式布置书架，返回书架整体可能的最小高度。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1105.Filling%20Bookcase%20Shelves/images/shelves.png" style="width: 337px; height: 500px;" /></p>

<pre>
<strong>输入：</strong>books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
<strong>输出：</strong>6
<strong>解释：</strong>
3 层书架的高度和为 1 + 3 + 2 = 6 。
第 2 本书不必放在第一层书架上。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> books = [[1,3],[2,4],[3,2]], shelfWidth = 6
<strong>输出:</strong> 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= books.length &lt;= 1000</code></li>
	<li><code>1 &lt;= thickness<sub>i</sub>&nbsp;&lt;= shelfWidth &lt;= 1000</code></li>
	<li><code>1 &lt;= height<sub>i</sub>&nbsp;&lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示前 $i$ 本书摆放的最小高度，初始时 $f[0] = 0$，答案为 $f[n]$。

考虑 $f[i]$，最后一本书为 $books[i - 1]$，其厚度为 $w$，高度为 $h$。

-   如果这本书单独摆放在新的一层，那么有 $f[i] = f[i - 1] + h$；
-   如果这本书可以与前面的最后几本书摆放在同一层，我们从后往前枚举同一层的第一本书 $boos[j-1]$，其中 $j \in [1, i - 1]$，将书的厚度累积到 $w$，如果 $w \gt shelfWidth$，说明此时的 $books[j-1]$ 已经无法与 $books[i-1]$ 摆放在同一层，停止枚举；否则我们更新当前层的最大高度 $h = \max(h, books[j-1][1])$，那么此时有 $f[i] = \min(f[i], f[j - 1] + h)$。

最终的答案即为 $f[n]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $books$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minHeightShelves(self, books: List[List[int]], shelfWidth: int) -> int:
        n = len(books)
        f = [0] * (n + 1)
        for i, (w, h) in enumerate(books, 1):
            f[i] = f[i - 1] + h
            for j in range(i - 1, 0, -1):
                w += books[j - 1][0]
                if w > shelfWidth:
                    break
                h = max(h, books[j - 1][1])
                f[i] = min(f[i], f[j - 1] + h)
        return f[n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int w = books[i - 1][0], h = books[i - 1][1];
            f[i] = f[i - 1] + h;
            for (int j = i - 1; j > 0; --j) {
                w += books[j - 1][0];
                if (w > shelfWidth) {
                    break;
                }
                h = Math.max(h, books[j - 1][1]);
                f[i] = Math.min(f[i], f[j - 1] + h);
            }
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minHeightShelves(vector<vector<int>>& books, int shelfWidth) {
        int n = books.size();
        int f[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int w = books[i - 1][0], h = books[i - 1][1];
            f[i] = f[i - 1] + h;
            for (int j = i - 1; j > 0; --j) {
                w += books[j - 1][0];
                if (w > shelfWidth) {
                    break;
                }
                h = max(h, books[j - 1][1]);
                f[i] = min(f[i], f[j - 1] + h);
            }
        }
        return f[n];
    }
};
```

### **Go**

```go
func minHeightShelves(books [][]int, shelfWidth int) int {
	n := len(books)
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		w, h := books[i-1][0], books[i-1][1]
		f[i] = f[i-1] + h
		for j := i - 1; j > 0; j-- {
			w += books[j-1][0]
			if w > shelfWidth {
				break
			}
			h = max(h, books[j-1][1])
			f[i] = min(f[i], f[j-1]+h)
		}
	}
	return f[n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minHeightShelves(books: number[][], shelfWidth: number): number {
    const n = books.length;
    const f = new Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        let [w, h] = books[i - 1];
        f[i] = f[i - 1] + h;
        for (let j = i - 1; j > 0; --j) {
            w += books[j - 1][0];
            if (w > shelfWidth) {
                break;
            }
            h = Math.max(h, books[j - 1][1]);
            f[i] = Math.min(f[i], f[j - 1] + h);
        }
    }
    return f[n];
}
```

### **C#**

```cs
public class Solution {
    public int MinHeightShelves(int[][] books, int shelfWidth) {
        int n = books.Length;
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int w = books[i - 1][0], h = books[i - 1][1];
            f[i] = f[i - 1] + h;
            for (int j = i - 1; j > 0; --j) {
                w += books[j - 1][0];
                if (w > shelfWidth) {
                    break;
                }
                h = Math.Max(h, books[j - 1][1]);
                f[i] = Math.Min(f[i], f[j - 1] + h);
            }
        }
        return f[n];
    }
}
```

### **...**

```

```

<!-- tabs:end -->
