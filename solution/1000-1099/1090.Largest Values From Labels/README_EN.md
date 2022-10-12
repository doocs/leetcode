# [1090. Largest Values From Labels](https://leetcode.com/problems/largest-values-from-labels)

[中文文档](/solution/1000-1099/1090.Largest%20Values%20From%20Labels/README.md)

## Description

<p>There is a set of <code>n</code> items. You are given two integer arrays <code>values</code> and <code>labels</code> where the value and the label of the <code>i<sup>th</sup></code> element are <code>values[i]</code> and <code>labels[i]</code> respectively. You are also given two integers <code>numWanted</code> and <code>useLimit</code>.</p>

<p>Choose a subset <code>s</code> of the <code>n</code> elements such that:</p>

<ul>
	<li>The size of the subset <code>s</code> is <strong>less than or equal to</strong> <code>numWanted</code>.</li>
	<li>There are <strong>at most</strong> <code>useLimit</code> items with the same label in <code>s</code>.</li>
</ul>

<p>The <strong>score</strong> of a subset is the sum of the values in the subset.</p>

<p>Return <em>the maximum <strong>score</strong> of a subset </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
<strong>Output:</strong> 9
<strong>Explanation:</strong> The subset chosen is the first, third, and fifth items.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
<strong>Output:</strong> 12
<strong>Explanation:</strong> The subset chosen is the first, second, and third items.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
<strong>Output:</strong> 16
<strong>Explanation:</strong> The subset chosen is the first and fourth items.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == values.length == labels.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= values[i], labels[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= numWanted, useLimit &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestValsFromLabels(
        self, values: List[int], labels: List[int], numWanted: int, useLimit: int
    ) -> int:
        arr = list(zip(values, labels))
        arr.sort(reverse=True)
        cnt = Counter()
        ans = num = 0
        for v, l in arr:
            if cnt[l] < useLimit:
                cnt[l] += 1
                num += 1
                ans += v
            if num == numWanted:
                break
        return ans
```

### **Java**

```java
class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        int[][] p = new int[n][2];
        for (int i = 0; i < n; ++i) {
            p[i] = new int[] {values[i], labels[i]};
        }
        Arrays.sort(p, (a, b) -> b[0] - a[0]);
        int ans = 0;
        int num = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < n && num < numWanted; ++i) {
            int v = p[i][0], l = p[i][1];
            if (counter.getOrDefault(l, 0) < useLimit) {
                counter.put(l, counter.getOrDefault(l, 0) + 1);
                ans += v;
                ++num;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestValsFromLabels(vector<int>& values, vector<int>& labels, int numWanted, int useLimit) {
        int n = values.size();
        vector<pair<int, int>> p;
        for (int i = 0; i < n; ++i) p.emplace_back(values[i], labels[i]);
        sort(p.begin(), p.end());
        unordered_map<int, int> counter;
        int ans = 0, num = 0;
        for (int i = n - 1; i >= 0 && num < numWanted; --i) {
            int v = p[i].first, l = p[i].second;
            if (counter[l] < useLimit) {
                ++counter[l];
                ++num;
                ans += v;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func largestValsFromLabels(values []int, labels []int, numWanted int, useLimit int) int {
	var p [][]int
	for i, v := range values {
		p = append(p, []int{v, labels[i]})
	}
	sort.Slice(p, func(i, j int) bool {
		return p[i][0] > p[j][0]
	})
	counter := make(map[int]int)
	ans, num := 0, 0
	for _, t := range p {
		if num >= numWanted {
			break
		}
		v, l := t[0], t[1]
		if counter[l] < useLimit {
			counter[l]++
			num++
			ans += v
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
