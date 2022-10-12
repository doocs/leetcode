# [632. Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists)

[中文文档](/solution/0600-0699/0632.Smallest%20Range%20Covering%20Elements%20from%20K%20Lists/README.md)

## Description

<p>You have <code>k</code> lists of sorted integers in <strong>non-decreasing&nbsp;order</strong>. Find the <b>smallest</b> range that includes at least one number from each of the <code>k</code> lists.</p>

<p>We define the range <code>[a, b]</code> is smaller than range <code>[c, d]</code> if <code>b - a &lt; d - c</code> <strong>or</strong> <code>a &lt; c</code> if <code>b - a == d - c</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
<strong>Output:</strong> [20,24]
<strong>Explanation: </strong>
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [[1,2,3],[1,2,3],[1,2,3]]
<strong>Output:</strong> [1,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums.length == k</code></li>
	<li><code>1 &lt;= k &lt;= 3500</code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 50</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i][j] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        t = [(x, i) for i, v in enumerate(nums) for x in v]
        t.sort()
        cnt = Counter()
        ans = [-inf, inf]
        j = 0
        for b, v in t:
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
