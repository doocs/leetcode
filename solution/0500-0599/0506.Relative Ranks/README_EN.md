---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0506.Relative%20Ranks/README_EN.md
tags:
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [506. Relative Ranks](https://leetcode.com/problems/relative-ranks)

[中文文档](/solution/0500-0599/0506.Relative%20Ranks/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>score</code> of size <code>n</code>, where <code>score[i]</code> is the score of the <code>i<sup>th</sup></code> athlete in a competition. All the scores are guaranteed to be <strong>unique</strong>.</p>

<p>The athletes are <strong>placed</strong> based on their scores, where the <code>1<sup>st</sup></code> place athlete has the highest score, the <code>2<sup>nd</sup></code> place athlete has the <code>2<sup>nd</sup></code> highest score, and so on. The placement of each athlete determines their rank:</p>

<ul>
	<li>The <code>1<sup>st</sup></code> place athlete&#39;s rank is <code>&quot;Gold Medal&quot;</code>.</li>
	<li>The <code>2<sup>nd</sup></code> place athlete&#39;s rank is <code>&quot;Silver Medal&quot;</code>.</li>
	<li>The <code>3<sup>rd</sup></code> place athlete&#39;s rank is <code>&quot;Bronze Medal&quot;</code>.</li>
	<li>For the <code>4<sup>th</sup></code> place to the <code>n<sup>th</sup></code> place athlete, their rank is their placement number (i.e., the <code>x<sup>th</sup></code> place athlete&#39;s rank is <code>&quot;x&quot;</code>).</li>
</ul>

<p>Return an array <code>answer</code> of size <code>n</code> where <code>answer[i]</code> is the <strong>rank</strong> of the <code>i<sup>th</sup></code> athlete.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> score = [5,4,3,2,1]
<strong>Output:</strong> [&quot;Gold Medal&quot;,&quot;Silver Medal&quot;,&quot;Bronze Medal&quot;,&quot;4&quot;,&quot;5&quot;]
<strong>Explanation:</strong> The placements are [1<sup>st</sup>, 2<sup>nd</sup>, 3<sup>rd</sup>, 4<sup>th</sup>, 5<sup>th</sup>].</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> score = [10,3,8,9,4]
<strong>Output:</strong> [&quot;Gold Medal&quot;,&quot;5&quot;,&quot;Bronze Medal&quot;,&quot;Silver Medal&quot;,&quot;4&quot;]
<strong>Explanation:</strong> The placements are [1<sup>st</sup>, 5<sup>th</sup>, 3<sup>rd</sup>, 2<sup>nd</sup>, 4<sup>th</sup>].

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == score.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= score[i] &lt;= 10<sup>6</sup></code></li>
	<li>All the values in <code>score</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We use an array $\textit{idx}$ to store the indices from $0$ to $n-1$, then sort $\textit{idx}$ based on the values in $\textit{score}$ in descending order.

Next, we define an array $\textit{top3} = [\text{Gold Medal}, \text{Silver Medal}, \text{Bronze Medal}]$. We traverse $\textit{idx}$, and for each index $j$, if $j$ is less than $3$, then $\textit{ans}[j]$ is $\textit{top3}[j]$; otherwise, it is $j+1$.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{score}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findRelativeRanks(self, score: List[int]) -> List[str]:
        n = len(score)
        idx = list(range(n))
        idx.sort(key=lambda x: -score[x])
        top3 = ["Gold Medal", "Silver Medal", "Bronze Medal"]
        ans = [None] * n
        for i, j in enumerate(idx):
            ans[j] = top3[i] if i < 3 else str(i + 1)
        return ans
```

#### Java

```java
class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i1, i2) -> score[i2] - score[i1]);
        String[] ans = new String[n];
        String[] top3 = new String[] {"Gold Medal", "Silver Medal", "Bronze Medal"};
        for (int i = 0; i < n; ++i) {
            ans[idx[i]] = i < 3 ? top3[i] : String.valueOf(i + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> findRelativeRanks(vector<int>& score) {
        int n = score.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&score](int a, int b) {
            return score[a] > score[b];
        });
        vector<string> ans(n);
        vector<string> top3 = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        for (int i = 0; i < n; ++i) {
            ans[idx[i]] = i < 3 ? top3[i] : to_string(i + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func findRelativeRanks(score []int) []string {
	n := len(score)
	idx := make([][]int, n)
	for i := 0; i < n; i++ {
		idx[i] = []int{score[i], i}
	}
	sort.Slice(idx, func(i1, i2 int) bool {
		return idx[i1][0] > idx[i2][0]
	})
	ans := make([]string, n)
	top3 := []string{"Gold Medal", "Silver Medal", "Bronze Medal"}
	for i := 0; i < n; i++ {
		if i < 3 {
			ans[idx[i][1]] = top3[i]
		} else {
			ans[idx[i][1]] = strconv.Itoa(i + 1)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function findRelativeRanks(score: number[]): string[] {
    const n = score.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((a, b) => score[b] - score[a]);
    const top3 = ['Gold Medal', 'Silver Medal', 'Bronze Medal'];
    const ans: string[] = Array(n);
    for (let i = 0; i < n; i++) {
        if (i < 3) {
            ans[idx[i]] = top3[i];
        } else {
            ans[idx[i]] = (i + 1).toString();
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
