# [1105. Filling Bookcase Shelves](https://leetcode.com/problems/filling-bookcase-shelves)

[中文文档](/solution/1100-1199/1105.Filling%20Bookcase%20Shelves/README.md)

## Description

<p>You are given an array <code>books</code> where <code>books[i] = [thickness<sub>i</sub>, height<sub>i</sub>]</code> indicates the thickness and height of the <code>i<sup>th</sup></code> book. You are also given an integer <code>shelfWidth</code>.</p>

<p>We want to place these books in order onto bookcase shelves that have a total width <code>shelfWidth</code>.</p>

<p>We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to <code>shelfWidth</code>, then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down. We repeat this process until there are no more books to place.</p>

<p>Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.</p>

<ul>
	<li>For example, if we have an ordered list of <code>5</code> books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.</li>
</ul>

<p>Return <em>the minimum possible height that the total bookshelf can be after placing shelves in this manner</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1105.Filling%20Bookcase%20Shelves/images/shelves.png" style="height: 500px; width: 337px;" />
<pre>
<strong>Input:</strong> books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong>
The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> books = [[1,3],[2,4],[3,2]], shelfWidth = 6
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= books.length &lt;= 1000</code></li>
	<li><code>1 &lt;= thickness<sub>i</sub> &lt;= shelfWidth &lt;= 1000</code></li>
	<li><code>1 &lt;= height<sub>i</sub> &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minHeightShelves(self, books: List[List[int]], shelfWidth: int) -> int:
        n = len(books)
        dp = [0] * (n + 1)
        for i, (w, h) in enumerate(books, 1):
            dp[i] = dp[i - 1] + h
            for j in range(i - 1, 0, -1):
                w += books[j - 1][0]
                if w > shelfWidth:
                    break
                h = max(h, books[j - 1][1])
                dp[i] = min(dp[i], dp[j - 1] + h)
        return dp[n]
```

### **Java**

```java
class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int w = books[i - 1][0], h = books[i - 1][1];
            dp[i] = dp[i - 1] + h;
            for (int j = i - 1; j > 0; --j) {
                w += books[j - 1][0];
                if (w > shelfWidth) {
                    break;
                }
                h = Math.max(h, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + h);
            }
        }
        return dp[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minHeightShelves(vector<vector<int>>& books, int shelfWidth) {
        int n = books.size();
        vector<int> dp(n + 1);
        for (int i = 1; i <= n; ++i) {
            int w = books[i - 1][0], h = books[i - 1][1];
            dp[i] = dp[i - 1] + h;
            for (int j = i - 1; j > 0; --j) {
                w += books[j - 1][0];
                if (w > shelfWidth) break;
                h = max(h, books[j - 1][1]);
                dp[i] = min(dp[i], dp[j - 1] + h);
            }
        }
        return dp[n];
    }
};
```

### **Go**

```go
func minHeightShelves(books [][]int, shelfWidth int) int {
	n := len(books)
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		w, h := books[i-1][0], books[i-1][1]
		dp[i] = dp[i-1] + h
		for j := i - 1; j > 0; j-- {
			w += books[j-1][0]
			if w > shelfWidth {
				break
			}
			h = max(h, books[j-1][1])
			dp[i] = min(dp[i], dp[j-1]+h)
		}
	}
	return dp[n]
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

### **...**

```

```

<!-- tabs:end -->
