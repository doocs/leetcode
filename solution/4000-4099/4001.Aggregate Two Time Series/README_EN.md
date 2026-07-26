---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4001.Aggregate%20Two%20Time%20Series/README_EN.md
---

<!-- problem:start -->

# [4001. Aggregate Two Time Series](https://leetcode.com/problems/aggregate-two-time-series)

[中文文档](/solution/4000-4099/4001.Aggregate%20Two%20Time%20Series/README.md)

## Description

<!-- description:start -->

<p>You are given two 2D integer arrays <code>series1</code> and <code>series2</code>.</p>

<p>Each element in both series is of the form <code>[timestamp, value]</code>, where:</p>

<ul>
	<li><code>timestamp</code> is an integer representing the time.</li>
	<li><code>value</code> is an integer representing the value at that timestamp.</li>
</ul>

<p>Each array is sorted in <span data-keyword="strictly-increasing-array">strictly increasing</span> order of <code>timestamp</code>.</p>

<p>For any timestamp <strong>not present</strong> in a series, its value is taken from the <strong>next available timestamp</strong> in the same series if one exists. Otherwise, its value is considered 0.</p>

<p>The <strong>aggregated series</strong> is formed by summing the corresponding values from both series at every timestamp that appears in either series.</p>

<p>Return the <strong>aggregated series</strong> as a 2D integer array of <code>[timestamp, summedValue]</code> pairs, sorted in <strong>strictly increasing</strong> order of timestamp.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">series1 = [[1,3],[4,1]], series2 = [[2,2],[5,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,5],[2,3],[4,3],[5,2]]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Timestamp</th>
			<th style="border: 1px solid black;"><code>series1</code></th>
			<th style="border: 1px solid black;"><code>series2</code></th>
			<th style="border: 1px solid black;"><code>summedValue</code></th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
	</tbody>
</table>

<p>Thus, the aggregated series is <code>[[1, 5], [2, 3], [4, 3], [5, 2]]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">series1 = [[1,5],[3,1]], series2 = [[2,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,7],[2,3],[3,1]]</span></p>

<p><strong>Explanation:</strong></p>

<table style="border: 1px solid black;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">Timestamp</th>
			<th style="border: 1px solid black;"><code>series1</code></th>
			<th style="border: 1px solid black;"><code>series2</code></th>
			<th style="border: 1px solid black;"><code>summedValue</code></th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">7</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>Thus, the aggregated series is <code>[[1, 7], [2, 3], [3, 1]]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">series1 = [[1,5]], series2 = [[1000000000,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1,7],[1000000000,2]]</span></p>

<p><strong>Explanation:</strong></p>

<p>At timestamp 1, the next available value in <code>series2</code> is 2 at timestamp 1000000000. At timestamp 1000000000, there is no later timestamp in <code>series1</code>, so its value is 0. Only timestamps that appear in at least one of the two series are included.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= series1.length, series2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>series1[i].length == series2[i].length == 2</code></li>
	<li><code>1 &lt;= series1[i][0], series2[i][0] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= series1[i][1], series2[i][1] &lt;= 10<sup>9</sup></code></li>
	<li>Each series is sorted in strictly increasing order of <code>timestamp</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

Both series are strictly increasing by timestamp, so they can be merged with two pointers. Taking the value of the next later timestamp for a missing timestamp is equivalent to: the value at the current pointer can be used directly for earlier missing timestamps in that series.

Let pointers $i$ and $j$ point to the two series. While both are not exhausted:

- If $t_1 = t_2$, output $[t_1, v_1 + v_2]$ and advance both pointers;
- If $t_1 < t_2$, output $[t_1, v_1 + v_2]$ (series2 uses the current later $v_2$) and advance only $i$;
- If $t_2 < t_1$, handle symmetrically.

After one series is exhausted, append the remaining points of the other series directly (there is no later timestamp on the opposite side, so its value is $0$).

The time complexity is $O(m + n)$, and the space complexity is $O(m + n)$, where $m$ and $n$ are the lengths of the two series.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def aggregateTimeSeries(
        self, series1: list[list[int]], series2: list[list[int]]
    ) -> list[list[int]]:
        m, n = len(series1), len(series2)
        i = j = 0
        ans = []
        while i < m and j < n:
            t1, v1 = series1[i]
            t2, v2 = series2[j]
            if t1 == t2:
                ans.append([t1, v1 + v2])
                i += 1
                j += 1
            elif t1 < t2:
                ans.append([t1, v1 + v2])
                i += 1
            else:
                ans.append([t2, v1 + v2])
                j += 1
        while i < m:
            ans.append(series1[i])
            i += 1
        while j < n:
            ans.append(series2[j])
            j += 1
        return ans
```

#### Java

```java
class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int m = series1.length, n = series2.length;
        int i = 0, j = 0;
        List<List<Integer>> ans = new ArrayList<>();

        while (i < m && j < n) {
            int t1 = series1[i][0], v1 = series1[i][1];
            int t2 = series2[j][0], v2 = series2[j][1];

            if (t1 == t2) {
                ans.add(List.of(t1, v1 + v2));
                i++;
                j++;
            } else if (t1 < t2) {
                ans.add(List.of(t1, v1 + v2));
                i++;
            } else {
                ans.add(List.of(t2, v1 + v2));
                j++;
            }
        }

        while (i < m) {
            ans.add(List.of(series1[i][0], series1[i][1]));
            i++;
        }

        while (j < n) {
            ans.add(List.of(series2[j][0], series2[j][1]));
            j++;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> aggregateTimeSeries(vector<vector<int>>& series1, vector<vector<int>>& series2) {
        int m = series1.size(), n = series2.size();
        int i = 0, j = 0;
        vector<vector<int>> ans;

        while (i < m && j < n) {
            int t1 = series1[i][0], v1 = series1[i][1];
            int t2 = series2[j][0], v2 = series2[j][1];

            if (t1 == t2) {
                ans.push_back({t1, v1 + v2});
                i++;
                j++;
            } else if (t1 < t2) {
                ans.push_back({t1, v1 + v2});
                i++;
            } else {
                ans.push_back({t2, v1 + v2});
                j++;
            }
        }

        while (i < m) {
            ans.push_back(series1[i]);
            i++;
        }

        while (j < n) {
            ans.push_back(series2[j]);
            j++;
        }

        return ans;
    }
};
```

#### Go

```go
func aggregateTimeSeries(series1 [][]int, series2 [][]int) [][]int {
	m, n := len(series1), len(series2)
	i, j := 0, 0
	ans := make([][]int, 0)

	for i < m && j < n {
		t1, v1 := series1[i][0], series1[i][1]
		t2, v2 := series2[j][0], series2[j][1]

		if t1 == t2 {
			ans = append(ans, []int{t1, v1 + v2})
			i++
			j++
		} else if t1 < t2 {
			ans = append(ans, []int{t1, v1 + v2})
			i++
		} else {
			ans = append(ans, []int{t2, v1 + v2})
			j++
		}
	}

	for i < m {
		ans = append(ans, series1[i])
		i++
	}

	for j < n {
		ans = append(ans, series2[j])
		j++
	}

	return ans
}
```

#### TypeScript

```ts
function aggregateTimeSeries(series1: number[][], series2: number[][]): number[][] {
    const m = series1.length;
    const n = series2.length;
    let i = 0;
    let j = 0;
    const ans: number[][] = [];

    while (i < m && j < n) {
        const [t1, v1] = series1[i];
        const [t2, v2] = series2[j];

        if (t1 === t2) {
            ans.push([t1, v1 + v2]);
            i++;
            j++;
        } else if (t1 < t2) {
            ans.push([t1, v1 + v2]);
            i++;
        } else {
            ans.push([t2, v1 + v2]);
            j++;
        }
    }

    while (i < m) {
        ans.push(series1[i]);
        i++;
    }

    while (j < n) {
        ans.push(series2[j]);
        j++;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
