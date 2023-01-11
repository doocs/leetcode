# [2343. 裁剪数字后查询第 K 小的数字](https://leetcode.cn/problems/query-kth-smallest-trimmed-number)

[English Version](/solution/2300-2399/2343.Query%20Kth%20Smallest%20Trimmed%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串数组&nbsp;<code>nums</code>&nbsp;，其中每个字符串 <strong>长度相等</strong>&nbsp;且只包含数字。</p>

<p>再给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>queries</code>&nbsp;，其中&nbsp;<code>queries[i] = [k<sub>i</sub>, trim<sub>i</sub>]</code>&nbsp;。对于每个&nbsp;<code>queries[i]</code>&nbsp;，你需要：</p>

<ul>
	<li>将&nbsp;<code>nums</code>&nbsp;中每个数字 <strong>裁剪</strong>&nbsp;到剩下 <strong>最右边</strong>&nbsp;<code>trim<sub>i</sub></code>&nbsp;个数位。</li>
	<li>在裁剪过后的数字中，找到 <code>nums</code>&nbsp;中第&nbsp;<code>k<sub>i</sub></code>&nbsp;小数字对应的 <strong>下标</strong>&nbsp;。如果两个裁剪后数字一样大，那么下标 <strong>更小</strong>&nbsp;的数字视为更小的数字。</li>
	<li>将 <code>nums</code>&nbsp;中每个数字恢复到原本字符串。</li>
</ul>

<p>请你返回一个长度与 <code><span style="">queries</span></code>&nbsp;相等的数组<em>&nbsp;</em><code>answer</code>，其中<em>&nbsp;</em><code>answer[i]</code>是第<em>&nbsp;</em><code>i</code><em>&nbsp;</em>次查询的结果。</p>

<p><strong>提示：</strong></p>

<ul>
	<li>裁剪到剩下最右边 <code>x</code>&nbsp;个数位的意思是不断删除最左边的数位，直到剩下 <code>x</code>&nbsp;个数位。</li>
	<li><code>nums</code>&nbsp;中的字符串可能会有前导 0 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
<b>输出：</b>[2,2,1,0]
<strong>解释：</strong>
1. 裁剪到只剩 1 个数位后，nums = ["2","3","1","4"] 。最小的数字是 1 ，下标为 2 。
2. 裁剪到剩 3 个数位后，nums 没有变化。第 2 小的数字是 251 ，下标为 2 。
3. 裁剪到剩 2 个数位后，nums = ["02","73","51","14"] 。第 4 小的数字是 73 ，下标为 1 。
4. 裁剪到剩 2 个数位后，最小数字是 2 ，下标为 0 。
   注意，裁剪后数字 "02" 值为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
<b>输出：</b>[3,0]
<strong>解释：</strong>
1. 裁剪到剩 1 个数位，nums = ["4","7","6","4"] 。第 2 小的数字是 4 ，下标为 3 。
   有两个 4 ，下标为 0 的 4 视为小于下标为 3 的 4 。
2. 裁剪到剩 2 个数位，nums 不变。第二小的数字是 24 ，下标为 0 。
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 100</code></li>
	<li><code>nums[i]</code> 只包含数字。</li>
	<li>所有&nbsp;<code>nums[i].length</code>&nbsp;的长度 <b>相同</b>&nbsp;。</li>
	<li><code>1 &lt;= queries.length &lt;= 100</code></li>
	<li><code>queries[i].length == 2</code></li>
	<li><code>1 &lt;= k<sub>i</sub> &lt;= nums.length</code></li>
	<li><code>1 &lt;= trim<sub>i</sub> &lt;= nums[0].length</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能使用 <strong>基数排序算法</strong> 解决此问题吗？这种解法的复杂度又是多少？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

根据题意，我们可以模拟裁剪过程，然后对裁剪后的字符串进行排序，最后根据下标找到对应的数字即可。

时间复杂度 $O(m \times \ n \times \log n \times s)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别为 `nums` 和 `queries` 的长度，而 $s$ 为 $nums[i]$ 字符串的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestTrimmedNumbers(
        self, nums: List[str], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        for k, trim in queries:
            t = sorted((v[-trim:], i) for i, v in enumerate(nums))
            ans.append(t[k - 1][1])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        String[][] t = new String[n][2];
        for (int i = 0; i < m; ++i) {
            int k = queries[i][0], trim = queries[i][1];
            for (int j = 0; j < n; ++j) {
                t[j] = new String[] {nums[j].substring(nums[j].length() - trim), String.valueOf(j)};
            }
            Arrays.sort(t, (a, b) -> {
                int x = a[0].compareTo(b[0]);
                return x == 0 ? Long.compare(Integer.valueOf(a[1]), Integer.valueOf(b[1])) : x;
            });
            ans[i] = Integer.valueOf(t[k - 1][1]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> smallestTrimmedNumbers(vector<string>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<pair<string, int>> t(n);
        vector<int> ans;
        for (auto& q : queries) {
            int k = q[0], trim = q[1];
            for (int j = 0; j < n; ++j) {
                t[j] = {nums[j].substr(nums[j].size() - trim), j};
            }
            sort(t.begin(), t.end());
            ans.push_back(t[k - 1].second);
        }
        return ans;
    }
};
```

### **Go**

```go
func smallestTrimmedNumbers(nums []string, queries [][]int) []int {
	type pair struct {
		s string
		i int
	}
	ans := make([]int, len(queries))
	t := make([]pair, len(nums))
	for i, q := range queries {
		for j, s := range nums {
			t[j] = pair{s[len(s)-q[1]:], j}
		}
		sort.Slice(t, func(i, j int) bool { a, b := t[i], t[j]; return a.s < b.s || a.s == b.s && a.i < b.i })
		ans[i] = t[q[0]-1].i
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
