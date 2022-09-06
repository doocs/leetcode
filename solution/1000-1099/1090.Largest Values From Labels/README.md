# [1090. 受标签影响的最大值](https://leetcode.cn/problems/largest-values-from-labels)

[English Version](/solution/1000-1099/1090.Largest%20Values%20From%20Labels/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们有一个&nbsp;<code>n</code>&nbsp;项的集合。给出两个整数数组&nbsp;<code>values</code>&nbsp;和 <code>labels</code>&nbsp;，第 <code>i</code> 个元素的值和标签分别是&nbsp;<code>values[i]</code>&nbsp;和&nbsp;<code>labels[i]</code>。还会给出两个整数&nbsp;<code>numWanted</code>&nbsp;和 <code>useLimit</code> 。</p>

<p>从 <code>n</code> 个元素中选择一个子集 <code>s</code> :</p>

<ul>
	<li>子集 <code>s</code> 的大小&nbsp;<strong>小于或等于</strong> <code>numWanted</code> 。</li>
	<li><code>s</code> 中 <strong>最多</strong> 有相同标签的 <code>useLimit</code> 项。</li>
</ul>

<p>一个子集的&nbsp;<strong>分数&nbsp;</strong>是该子集的值之和。</p>

<p>返回子集&nbsp;<code>s</code> 的最大 <strong>分数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>values = [5,4,3,2,1], labels = [1,1,2,2,3], numWanted = 3, useLimit = 1
<strong>输出：</strong>9
<strong>解释：</strong>选出的子集是第一项，第三项和第五项。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>values = [5,4,3,2,1], labels = [1,3,3,3,2], numWanted = 3, useLimit = 2
<strong>输出：</strong>12
<strong>解释：</strong>选出的子集是第一项，第二项和第三项。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>values = [9,8,8,7,6], labels = [0,0,0,1,1], numWanted = 3, useLimit = 1
<strong>输出：</strong>16
<strong>解释：</strong>选出的子集是第一项和第四项。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == values.length == labels.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= values[i], labels[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= numWanted, useLimit &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
