# [1787. Make the XOR of All Segments Equal to Zero](https://leetcode.com/problems/make-the-xor-of-all-segments-equal-to-zero)

[中文文档](/solution/1700-1799/1787.Make%20the%20XOR%20of%20All%20Segments%20Equal%20to%20Zero/README.md)

## Description

<p>You are given an array <code>nums</code>​​​ and an integer <code>k</code>​​​​​. The <font face="monospace">XOR</font> of a segment <code>[left, right]</code> where <code>left &lt;= right</code> is the <code>XOR</code> of all the elements with indices between <code>left</code> and <code>right</code>, inclusive: <code>nums[left] XOR nums[left+1] XOR ... XOR nums[right]</code>.</p>

<p>Return <em>the minimum number of elements to change in the array </em>such that the <code>XOR</code> of all segments of size <code>k</code>​​​​​​ is equal to zero.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,0,3,0], k = 1
<strong>Output:</strong> 3
<strong>Explanation: </strong>Modify the array from [<u><strong>1</strong></u>,<u><strong>2</strong></u>,0,<u><strong>3</strong></u>,0] to from [<u><strong>0</strong></u>,<u><strong>0</strong></u>,0,<u><strong>0</strong></u>,0].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,5,2,1,7,3,4,7], k = 3
<strong>Output:</strong> 3
<strong>Explanation: </strong>Modify the array from [3,4,<strong><u>5</u></strong>,<strong><u>2</u></strong>,<strong><u>1</u></strong>,7,3,4,7] to [3,4,<strong><u>7</u></strong>,<strong><u>3</u></strong>,<strong><u>4</u></strong>,7,3,4,7].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,4,1,2,5,1,2,6], k = 3
<strong>Output:</strong> 3
<strong>Explanation: </strong>Modify the array from [1,2,<strong><u>4,</u></strong>1,2,<strong><u>5</u></strong>,1,2,<strong><u>6</u></strong>] to [1,2,<strong><u>3</u></strong>,1,2,<strong><u>3</u></strong>,1,2,<strong><u>3</u></strong>].</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 2000</code></li>
	<li><code>​​​​​​0 &lt;= nums[i] &lt; 2<sup>10</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minChanges(self, nums: List[int], k: int) -> int:
        n = 1 << 10
        cnt = [Counter() for _ in range(k)]
        size = [0] * k
        for i, v in enumerate(nums):
            cnt[i % k][v] += 1
            size[i % k] += 1
        f = [inf] * n
        f[0] = 0
        for i in range(k):
            g = [min(f) + size[i]] * n
            for j in range(n):
                for v, c in cnt[i].items():
                    g[j] = min(g[j], f[j ^ v] + size[i] - c)
            f = g
        return f[0]
```

### **Java**

```java
class Solution {
    public int minChanges(int[] nums, int k) {
        int n = 1 << 10;
        Map<Integer, Integer>[] cnt = new Map[k];
        int[] size = new int[k];
        for (int i = 0; i < k; ++i) {
            cnt[i] = new HashMap<>();
        }
        for (int i = 0; i < nums.length; ++i) {
            cnt[i % k].put(nums[i], cnt[i % k].getOrDefault(nums[i], 0) + 1);
            size[i % k]++;
        }
        int[] f = new int[n];
        Arrays.fill(f, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            int[] g = new int[n];
            Arrays.fill(g, min(f) + size[i]);
            for (int j = 0; j < n; ++j) {
                for (var e : cnt[i].entrySet()) {
                    int v = e.getKey(), c = e.getValue();
                    g[j] = Math.min(g[j], f[j ^ v] + size[i] - c);
                }
            }
            f = g;
        }
        return f[0];
    }

    private int min(int[] arr) {
        int mi = arr[0];
        for (int v : arr) {
            mi = Math.min(mi, v);
        }
        return mi;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minChanges(vector<int>& nums, int k) {
        int n = 1 << 10;
        vector<unordered_map<int, int>> cnt(k);
        vector<int> size(k);
        for (int i = 0; i < nums.size(); ++i) {
            cnt[i % k][nums[i]]++;
            size[i % k]++;
        }
        vector<int> f(n, 0x3f3f3f3f);
        f[0] = 0;
        for (int i = 0; i < k; ++i) {
            int mi = *min_element(f.begin(), f.end());
            vector<int> g(n, mi + size[i]);
            for (int j = 0; j < n; ++j) {
                for (auto& [v, c] : cnt[i]) {
                    g[j] = min(g[j], f[j ^ v] + size[i] - c);
                }
            }
            f = move(g);
        }
        return f[0];
    }
};
```

### **Go**

```go
func minChanges(nums []int, k int) int {
	n := 1 << 10
	cnt := make([]map[int]int, k)
	for i := range cnt {
		cnt[i] = map[int]int{}
	}
	size := make([]int, k)
	for i, v := range nums {
		cnt[i%k][v]++
		size[i%k]++
	}
	f := make([]int, n)
	for i := 1; i < n; i++ {
		f[i] = 0x3f3f3f3f
	}
	for i, sz := range size {
		g := make([]int, n)
		x := min(f...) + sz
		for i := range g {
			g[i] = x
		}
		for j := 0; j < n; j++ {
			for v, c := range cnt[i] {
				g[j] = min(g[j], f[j^v]+sz-c)
			}
		}
		f = g
	}
	return f[0]
}

func min(a ...int) int {
	mi := a[0]
	for _, v := range a {
		if mi > v {
			mi = v
		}
	}
	return mi
}
```

### **...**

```

```

<!-- tabs:end -->
