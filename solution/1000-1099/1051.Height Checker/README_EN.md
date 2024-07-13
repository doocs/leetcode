---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1051.Height%20Checker/README_EN.md
rating: 1303
source: Weekly Contest 138 Q1
tags:
    - Array
    - Counting Sort
    - Sorting
---

<!-- problem:start -->

# [1051. Height Checker](https://leetcode.com/problems/height-checker)

[中文文档](/solution/1000-1099/1051.Height%20Checker/README.md)

## Description

<!-- description:start -->

<p>A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in <strong>non-decreasing order</strong> by height. Let this ordering be represented by the integer array <code>expected</code> where <code>expected[i]</code> is the expected height of the <code>i<sup>th</sup></code> student in line.</p>

<p>You are given an integer array <code>heights</code> representing the <strong>current order</strong> that the students are standing in. Each <code>heights[i]</code> is the height of the <code>i<sup>th</sup></code> student in line (<strong>0-indexed</strong>).</p>

<p>Return <em>the <strong>number of indices</strong> where </em><code>heights[i] != expected[i]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> heights = [1,1,4,2,1,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
heights:  [1,1,<u>4</u>,2,<u>1</u>,<u>3</u>]
expected: [1,1,<u>1</u>,2,<u>3</u>,<u>4</u>]
Indices 2, 4, and 5 do not match.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> heights = [5,1,2,3,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
heights:  [<u>5</u>,<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>]
expected: [<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>,<u>5</u>]
All indices do not match.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> heights = [1,2,3,4,5]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
heights:  [1,2,3,4,5]
expected: [1,2,3,4,5]
All indices match.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= heights.length &lt;= 100</code></li>
	<li><code>1 &lt;= heights[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

We can first sort the heights of the students, then compare the sorted heights with the original heights, and count the positions that are different.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the number of students.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def heightChecker(self, heights: List[int]) -> int:
        expected = sorted(heights)
        return sum(a != b for a, b in zip(heights, expected))
```

#### Java

```java
class Solution {
    public int heightChecker(int[] heights) {
        int[] expected = heights.clone();
        Arrays.sort(expected);
        int ans = 0;
        for (int i = 0; i < heights.length; ++i) {
            if (heights[i] != expected[i]) {
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int heightChecker(vector<int>& heights) {
        vector<int> expected = heights;
        sort(expected.begin(), expected.end());
        int ans = 0;
        for (int i = 0; i < heights.size(); ++i) {
            ans += heights[i] != expected[i];
        }
        return ans;
    }
};
```

#### Go

```go
func heightChecker(heights []int) (ans int) {
	expected := slices.Clone(heights)
	sort.Ints(expected)
	for i, v := range heights {
		if v != expected[i] {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function heightChecker(heights: number[]): number {
    const expected = [...heights].sort((a, b) => a - b);
    return heights.reduce((acc, cur, i) => acc + (cur !== expected[i] ? 1 : 0), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Counting Sort

Since the height of the students in the problem does not exceed $100$, we can use counting sort. Here we use an array $cnt$ of length $101$ to count the number of times each height $h_i$ appears.

The time complexity is $O(n + M)$, and the space complexity is $O(M)$. Where $n$ is the number of students, and $M$ is the maximum height of the students. In this problem, $M = 101$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def heightChecker(self, heights: List[int]) -> int:
        cnt = [0] * 101
        for h in heights:
            cnt[h] += 1
        ans = i = 0
        for j in range(1, 101):
            while cnt[j]:
                cnt[j] -= 1
                if heights[i] != j:
                    ans += 1
                i += 1
        return ans
```

#### Java

```java
class Solution {
    public int heightChecker(int[] heights) {
        int[] cnt = new int[101];
        for (int h : heights) {
            ++cnt[h];
        }
        int ans = 0;
        for (int i = 0, j = 0; i < 101; ++i) {
            while (cnt[i] > 0) {
                --cnt[i];
                if (heights[j++] != i) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int heightChecker(vector<int>& heights) {
        vector<int> cnt(101);
        for (int& h : heights) ++cnt[h];
        int ans = 0;
        for (int i = 0, j = 0; i < 101; ++i) {
            while (cnt[i]) {
                --cnt[i];
                if (heights[j++] != i) ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func heightChecker(heights []int) int {
	cnt := make([]int, 101)
	for _, h := range heights {
		cnt[h]++
	}
	ans := 0
	for i, j := 0, 0; i < 101; i++ {
		for cnt[i] > 0 {
			cnt[i]--
			if heights[j] != i {
				ans++
			}
			j++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function heightChecker(heights: number[]): number {
    const cnt = Array(101).fill(0);
    for (const i of heights) {
        cnt[i]++;
    }
    let ans = 0;
    for (let j = 1, i = 0; j < 101; j++) {
        while (cnt[j]--) {
            if (heights[i++] !== j) {
                ans++;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
