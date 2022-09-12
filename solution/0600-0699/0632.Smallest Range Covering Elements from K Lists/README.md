# [632. 最小区间](https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists)

[English Version](/solution/0600-0699/0632.Smallest%20Range%20Covering%20Elements%20from%20K%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有&nbsp;<code>k</code>&nbsp;个 <strong>非递减排列</strong> 的整数列表。找到一个 <strong>最小 </strong>区间，使得&nbsp;<code>k</code>&nbsp;个列表中的每个列表至少有一个数包含在其中。</p>

<p>我们定义如果&nbsp;<code>b-a &lt; d-c</code>&nbsp;或者在&nbsp;<code>b-a == d-c</code>&nbsp;时&nbsp;<code>a &lt; c</code>，则区间 <code>[a,b]</code> 比 <code>[c,d]</code> 小。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
<strong>输出：</strong>[20,24]
<strong>解释：</strong> 
列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [[1,2,3],[1,2,3],[1,2,3]]
<strong>输出：</strong>[1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length == k</code></li>
	<li><code>1 &lt;= k &lt;= 3500</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 50</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 按非递减顺序排列</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 滑动窗口**

将每个数字 $v$ 及其所在的组 $i$，构成数据项 $(v, i)$，存放在一个新的列表或者数组中，记为 `t`。

对 `t` 按照数字的大小进行排序（类似于将多个有序数组合并成一个新的有序数组）。

然后遍历 `t` 中每个数据项，只看其中数字所在的组，用哈希表记录滑动窗口内的数字出现的组，如果组数为 $k$，说明当前窗口满足题目要求，此时算出窗口的起始和结束位置，更新答案。

时间复杂度 $O(n\log n)$。其中 $n$ 是所有数字的总数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        t = [(x, i) for i, v in enumerate(nums) for x in v]
        t.sort()
        cnt = Counter()
        ans = [-inf, inf]
        j = 0
        for i, (b, v) in enumerate(t):
            cnt[v] += 1
            while len(cnt) == len(nums):
                a = t[j][0]
                x = b - a - (ans[1] - ans[0])
                if x < 0 or (x == 0 and a < ans[0]):
                    ans = [a, b]
                w = t[j][1]
                cnt[w] -= 1
                if cnt[w] == 0:
                    cnt.pop(w)
                j += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = 0;
        for (var v : nums) {
            n += v.size();
        }
        int[][] t = new int[n][2];
        int k = nums.size();
        for (int i = 0, j = 0; i < k; ++i) {
            for (int x : nums.get(i)) {
                t[j++] = new int[] {x, i};
            }
        }
        Arrays.sort(t, (a, b) -> a[0] - b[0]);
        int j = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int[] ans = new int[] {-1000000, 1000000};
        for (int[] e : t) {
            int b = e[0];
            int v = e[1];
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
            while (cnt.size() == k) {
                int a = t[j][0];
                int w = t[j][1];
                int x = b - a - (ans[1] - ans[0]);
                if (x < 0 || (x == 0 && a < ans[0])) {
                    ans[0] = a;
                    ans[1] = b;
                }
                cnt.put(w, cnt.get(w) - 1);
                if (cnt.get(w) == 0) {
                    cnt.remove(w);
                }
                ++j;
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
    vector<int> smallestRange(vector<vector<int>>& nums) {
        int n = 0;
        for (auto& v : nums) n += v.size();
        vector<pair<int, int>> t(n);
        int k = nums.size();
        for (int i = 0, j = 0; i < k; ++i) {
            for (int v : nums[i]) {
                t[j++] = {v, i};
            }
        }
        sort(t.begin(), t.end());
        int j = 0;
        unordered_map<int, int> cnt;
        vector<int> ans = {-1000000, 1000000};
        for (int i = 0; i < n; ++i) {
            int b = t[i].first;
            int v = t[i].second;
            ++cnt[v];
            while (cnt.size() == k) {
                int a = t[j].first;
                int w = t[j].second;
                int x = b - a - (ans[1] - ans[0]);
                if (x < 0 || (x == 0 && a < ans[0])) {
                    ans[0] = a;
                    ans[1] = b;
                }
                if (--cnt[w] == 0) {
                    cnt.erase(w);
                }
                ++j;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func smallestRange(nums [][]int) []int {
	t := [][]int{}
	for i, x := range nums {
		for _, v := range x {
			t = append(t, []int{v, i})
		}
	}
	sort.Slice(t, func(i, j int) bool { return t[i][0] < t[j][0] })
	ans := []int{-1000000, 1000000}
	j := 0
	cnt := map[int]int{}
	for _, x := range t {
		b, v := x[0], x[1]
		cnt[v]++
		for len(cnt) == len(nums) {
			a, w := t[j][0], t[j][1]
			x := b - a - (ans[1] - ans[0])
			if x < 0 || (x == 0 && a < ans[0]) {
				ans[0], ans[1] = a, b
			}
			cnt[w]--
			if cnt[w] == 0 {
				delete(cnt, w)
			}
			j++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
