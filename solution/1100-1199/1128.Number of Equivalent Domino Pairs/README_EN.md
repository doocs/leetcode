---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1128.Number%20of%20Equivalent%20Domino%20Pairs/README_EN.md
rating: 1332
source: Weekly Contest 146 Q1
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [1128. Number of Equivalent Domino Pairs](https://leetcode.com/problems/number-of-equivalent-domino-pairs)

[中文文档](/solution/1100-1199/1128.Number%20of%20Equivalent%20Domino%20Pairs/README.md)

## Description

<!-- description:start -->

<p>Given a list of <code>dominoes</code>, <code>dominoes[i] = [a, b]</code> is <strong>equivalent to</strong> <code>dominoes[j] = [c, d]</code> if and only if either (<code>a == c</code> and <code>b == d</code>), or (<code>a == d</code> and <code>b == c</code>) - that is, one domino can be rotated to be equal to another domino.</p>

<p>Return <em>the number of pairs </em><code>(i, j)</code><em> for which </em><code>0 &lt;= i &lt; j &lt; dominoes.length</code><em>, and </em><code>dominoes[i]</code><em> is <strong>equivalent to</strong> </em><code>dominoes[j]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[2,1],[3,4],[5,6]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dominoes.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>dominoes[i].length == 2</code></li>
	<li><code>1 &lt;= dominoes[i][j] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can concatenate the two numbers of each domino in order of size to form a two-digit number, so that equivalent dominoes can be concatenated into the same two-digit number. For example, both `[1, 2]` and `[2, 1]` are concatenated into the two-digit number `12`, and both `[3, 4]` and `[4, 3]` are concatenated into the two-digit number `34`.

Then we traverse all the dominoes, using an array $cnt$ of length $100$ to record the number of occurrences of each two-digit number. For each domino, the two-digit number we concatenate is $x$, then the answer will increase by $cnt[x]$, and then we add $1$ to the value of $cnt[x]$. Continue to traverse the next domino, and we can count the number of all equivalent domino pairs.

The time complexity is $O(n)$, and the space complexity is $O(C)$. Here, $n$ is the number of dominoes, and $C$ is the maximum number of two-digit numbers concatenated in the dominoes, which is $100$.

<!-- tabs:start -->

```python
class Solution:
    def numEquivDominoPairs(self, dominoes: List[List[int]]) -> int:
        cnt = Counter()
        ans = 0
        for a, b in dominoes:
            x = a * 10 + b if a < b else b * 10 + a
            ans += cnt[x]
            cnt[x] += 1
        return ans
```

```java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] cnt = new int[100];
        int ans = 0;
        for (var e : dominoes) {
            int x = e[0] < e[1] ? e[0] * 10 + e[1] : e[1] * 10 + e[0];
            ans += cnt[x]++;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numEquivDominoPairs(vector<vector<int>>& dominoes) {
        int cnt[100]{};
        int ans = 0;
        for (auto& e : dominoes) {
            int x = e[0] < e[1] ? e[0] * 10 + e[1] : e[1] * 10 + e[0];
            ans += cnt[x]++;
        }
        return ans;
    }
};
```

```go
func numEquivDominoPairs(dominoes [][]int) (ans int) {
	cnt := [100]int{}
	for _, e := range dominoes {
		x := e[0]*10 + e[1]
		if e[0] > e[1] {
			x = e[1]*10 + e[0]
		}
		ans += cnt[x]
		cnt[x]++
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
