---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/README_EN.md
---

<!-- problem:start -->

# [3160. Find the Number of Distinct Colors Among the Balls](https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls)

[中文文档](/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>limit</code> and a 2D array <code>queries</code> of size <code>n x 2</code>.</p>

<p>There are <code>limit + 1</code> balls with <strong>distinct</strong> labels in the range <code>[0, limit]</code>. Initially, all balls are uncolored. For every query in <code>queries</code> that is of the form <code>[x, y]</code>, you mark ball <code>x</code> with the color <code>y</code>. After each query, you need to find the number of <strong>distinct</strong> colors among the balls.</p>

<p>Return an array <code>result</code> of length <code>n</code>, where <code>result[i]</code> denotes the number of distinct colors <em>after</em> <code>i<sup>th</sup></code> query.</p>

<p><strong>Note</strong> that when answering a query, lack of a color <em>will not</em> be considered as a color.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,2,3]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/images/ezgifcom-crop.gif" style="width: 455px; height: 145px;" /></p>

<ul>
	<li>After query 0, ball 1 has color 4.</li>
	<li>After query 1, ball 1 has color 4, and ball 2 has color 5.</li>
	<li>After query 2, ball 1 has color 3, and ball 2 has color 5.</li>
	<li>After query 3, ball 1 has color 3, ball 2 has color 5, and ball 3 has color 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,2,3,4]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3100-3199/3160.Find%20the%20Number%20of%20Distinct%20Colors%20Among%20the%20Balls/images/ezgifcom-crop2.gif" style="width: 457px; height: 144px;" /></strong></p>

<ul>
	<li>After query 0, ball 0 has color 1.</li>
	<li>After query 1, ball 0 has color 1, and ball 1 has color 2.</li>
	<li>After query 2, ball 0 has color 1, and balls 1 and 2 have color 2.</li>
	<li>After query 3, ball 0 has color 1, balls 1 and 2 have color 2, and ball 3 has color 4.</li>
	<li>After query 4, ball 0 has color 1, balls 1 and 2 have color 2, ball 3 has color 4, and ball 4 has color 5.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= limit &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n == queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>0 &lt;= queries[i][0] &lt;= limit</code></li>
	<li><code>1 &lt;= queries[i][1] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Double Hash Tables

We use a hash table `g` to record the color of each ball, and another hash table `cnt` to record the count of each color.

Next, we traverse the array `queries`. For each query $(x, y)$, we increase the count of color $y$ by $1$, then check whether ball $x$ has been colored. If it has, we decrease the count of the color of ball $x$ by $1$. If the count drops to $0$, we remove it from the hash table `cnt`. Then, we update the color of ball $x$ to $y$, and add the current size of the hash table `cnt` to the answer array.

After the traversal, we return the answer array.

The time complexity is $O(m)$, and the space complexity is $O(m)$, where $m$ is the length of the array `queries`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def queryResults(self, limit: int, queries: List[List[int]]) -> List[int]:
        g = {}
        cnt = Counter()
        ans = []
        for x, y in queries:
            cnt[y] += 1
            if x in g:
                cnt[g[x]] -= 1
                if cnt[g[x]] == 0:
                    cnt.pop(g[x])
            g[x] = y
            ans.append(len(cnt))
        return ans
```

#### Java

```java
class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> g = new HashMap<>();
        Map<Integer, Integer> cnt = new HashMap<>();
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = queries[i][0], y = queries[i][1];
            cnt.merge(y, 1, Integer::sum);
            if (g.containsKey(x) && cnt.merge(g.get(x), -1, Integer::sum) == 0) {
                cnt.remove(g.get(x));
            }
            g.put(x, y);
            ans[i] = cnt.size();
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> queryResults(int limit, vector<vector<int>>& queries) {
        unordered_map<int, int> g;
        unordered_map<int, int> cnt;
        vector<int> ans;
        for (auto& q : queries) {
            int x = q[0], y = q[1];
            cnt[y]++;
            if (g.contains(x) && --cnt[g[x]] == 0) {
                cnt.erase(g[x]);
            }
            g[x] = y;
            ans.push_back(cnt.size());
        }
        return ans;
    }
};
```

#### Go

```go
func queryResults(limit int, queries [][]int) (ans []int) {
	g := map[int]int{}
	cnt := map[int]int{}
	for _, q := range queries {
		x, y := q[0], q[1]
		cnt[y]++
		if v, ok := g[x]; ok {
			cnt[v]--
			if cnt[v] == 0 {
				delete(cnt, v)
			}
		}
		g[x] = y
		ans = append(ans, len(cnt))
	}
	return
}
```

#### TypeScript

```ts
function queryResults(limit: number, queries: number[][]): number[] {
    const g = new Map<number, number>();
    const cnt = new Map<number, number>();
    const ans: number[] = [];
    for (const [x, y] of queries) {
        cnt.set(y, (cnt.get(y) ?? 0) + 1);
        if (g.has(x)) {
            const v = g.get(x)!;
            cnt.set(v, cnt.get(v)! - 1);
            if (cnt.get(v) === 0) {
                cnt.delete(v);
            }
        }
        g.set(x, y);
        ans.push(cnt.size);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
