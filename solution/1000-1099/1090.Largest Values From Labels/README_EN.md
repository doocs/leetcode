# [1090. Largest Values From Labels](https://leetcode.com/problems/largest-values-from-labels)

[中文文档](/solution/1000-1099/1090.Largest%20Values%20From%20Labels/README.md)

## Description

<p>We have a set of items: the <code>i</code>-th item has value <code>values[i]</code> and label <code>labels[i]</code>.</p>

<p>Then, we choose&nbsp;a subset <code>S</code> of these items, such that:</p>

<ul>
	<li><code>|S| &lt;= num_wanted</code></li>
	<li>For every label <code>L</code>, the number of items in <code>S</code> with&nbsp;label <code>L</code> is <code>&lt;= use_limit</code>.</li>
</ul>

<p>Return the largest possible sum of the subset <code>S</code>.</p>

<p>&nbsp;</p>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>values = <span id="example-input-1-1">[5,4,3,2,1]</span>, labels = <span id="example-input-1-2">[1,1,2,2,3]</span>, <code>num_wanted </code>= <span id="example-input-1-3">3</span>, use_limit = <span id="example-input-1-4">1</span>

<strong>Output: </strong><span id="example-output-1">9</span>

<strong>Explanation: </strong>The subset chosen is the first, third, and fifth item.

</pre>

<div>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>values = <span id="example-input-2-1">[5,4,3,2,1]</span>, labels = <span id="example-input-2-2">[1,3,3,3,2]</span>, <code>num_wanted </code>= <span id="example-input-2-3">3</span>, use_limit = <span id="example-input-2-4">2</span>

<strong>Output: </strong><span id="example-output-2">12</span>

<strong>Explanation: </strong>The subset chosen is the first, second, and third item.

</pre>

<div>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input: </strong>values = <span id="example-input-3-1">[9,8,8,7,6]</span>, labels = <span id="example-input-3-2">[0,0,0,1,1]</span>, <code>num_wanted </code>= <span id="example-input-3-3">3</span>, use_limit = <span id="example-input-3-4">1</span>

<strong>Output:</strong>&nbsp;16

<strong>Explanation: </strong>The subset chosen is the first and fourth item.

</pre>

<div>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input: </strong>values = <span id="example-input-4-1">[9,8,8,7,6]</span>, labels = <span id="example-input-4-2">[0,0,0,1,1]</span>, <code>num_wanted </code>= <span id="example-input-4-3">3</span>, use_limit = <span id="example-input-4-4">2</span>

<strong>Output: </strong><span id="example-output-4">24</span>

<strong>Explanation: </strong>The subset chosen is the first, second, and fourth item.

</pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= values.length == labels.length &lt;= 20000</code></li>
	<li><code>0 &lt;= values[i], labels[i]&nbsp;&lt;= 20000</code></li>
	<li><code>1 &lt;= num_wanted, use_limit&nbsp;&lt;= values.length</code></li>
</ol>

</div>

</div>

</div>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestValsFromLabels(self, values: List[int], labels: List[int], numWanted: int, useLimit: int) -> int:
        n = len(values)
        idx = list(range(n))
        idx.sort(key=lambda i: -values[i])
        ans = num = 0
        counter = Counter()
        for i in idx:
            v, l = values[i], labels[i]
            if counter[l] < useLimit:
                counter[l] += 1
                ans += v
                num += 1
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
            p[i] = new int[]{values[i], labels[i]};
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
        for (int i = n - 1; i >= 0 && num < numWanted; --i)
        {
            int v = p[i].first, l = p[i].second;
            if (counter[l] < useLimit)
            {
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
