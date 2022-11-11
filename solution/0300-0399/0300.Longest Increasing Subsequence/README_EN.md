# [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence)

[中文文档](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)

## Description

<p>Given an integer array <code>nums</code>, return <em>the length of the longest <strong>strictly increasing </strong></em><span data-keyword="subsequence-array"><em><strong>subsequence</strong></em></span>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,9,2,5,3,7,101,18]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0,3,2,3]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,7,7,7,7,7,7]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2500</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><b>Follow up:</b>&nbsp;Can you come up with an algorithm that runs in&nbsp;<code>O(n log(n))</code> time complexity?</p>

## Solutions

Dynamic programming or Binary Indexed Tree.

<!-- tabs:start -->

### **Python3**

Dynamic programming:

```python
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [1] * n
        for i in range(1, n):
            for j in range(i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i], dp[j] + 1)
        return max(dp)
```

Greedy & Binary search:

```python
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        d = [nums[0]]
        for x in nums[1:]:
            if x > d[-1]:
                d.append(x)
            else:
                idx = bisect_left(d, x)
                if idx == len(d):
                    idx = 0
                d[idx] = x
        return len(d)
```

Binary Indexed Tree:

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, val):
        while x <= self.n:
            self.c[x] = max(self.c[x], val)
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        s = 0
        while x:
            s = max(s, self.c[x])
            x -= BinaryIndexedTree.lowbit(x)
        return s


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        s = sorted(set(nums))
        m = {v: i for i, v in enumerate(s, 1)}
        tree = BinaryIndexedTree(len(m))
        ans = 1
        for v in nums:
            x = m[v]
            t = tree.query(x - 1) + 1
            ans = max(ans, t)
            tree.update(x, t)
        return ans
```

### **Java**

Dynamic programming:

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

Greedy & Binary search:

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] d = new int[n + 1];
        d[1] = nums[0];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[size]) {
                d[++size] = nums[i];
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                int p = d[left] >= nums[i] ? left : 1;
                d[p] = nums[i];
            }
        }
        return size;
    }
}
```

Binary Indexed Tree:

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : ts) {
            m.put(v, idx++);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m.size());
        int ans = 1;
        for (int v : nums) {
            int x = m.get(v);
            int t = tree.query(x - 1) + 1;
            ans = Math.max(ans, t);
            tree.update(x, t);
        }
        return ans;
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= lowbit(x);
        }
        return s;
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

### **TypeScript**

Dynamic programming:

```ts
function lengthOfLIS(nums: number[]): number {
    let n = nums.length;
    let dp = new Array(n).fill(1);
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    return Math.max(...dp);
}
```

Greedy & Binary search:

```ts
function lengthOfLIS(nums: number[]): number {
    const n = nums.length;
    let d = new Array(n + 1);
    d[1] = nums[0];
    let size = 1;
    for (let i = 1; i < n; ++i) {
        if (nums[i] > d[size]) {
            d[++size] = nums[i];
        } else {
            let left = 1,
                right = size;
            while (left < right) {
                const mid = (left + right) >> 1;
                if (d[mid] >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            const p = d[left] >= nums[i] ? left : 1;
            d[p] = nums[i];
        }
    }
    return size;
}
```

### **C++**

Dynamic programming:

```cpp
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) dp[i] = max(dp[i], dp[j] + 1);
            }
        }
        return *max_element(dp.begin(), dp.end());
    }
};
```

Greedy & Binary search:

```cpp
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        vector<int> d {nums[0]};
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[d.size() - 1])
                d.push_back(nums[i]);
            else {
                int idx = lower_bound(d.begin(), d.end(), nums[i]) - d.begin();
                if (idx == d.size()) idx = 0;
                d[idx] = nums[i];
            }
        }
        return d.size();
    }
};
```

Binary Indexed Tree:

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int val) {
        while (x <= n)
        {
            c[x] = max(c[x], val);
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
            s = max(s, c[x]);
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        set<int> s(nums.begin(), nums.end());
        int idx = 1;
        unordered_map<int, int> m;
        for (int v : s) m[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int ans = 1;
        for (int v : nums)
        {
            int x = m[v];
            int t = tree->query(x - 1) + 1;
            ans = max(ans, t);
            tree->update(x, t);
        }
        return ans;
    }
};
```

### **Go**

Dynamic programming:

```go
func lengthOfLIS(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	dp[0] = 1
	res := 1
	for i := 1; i < n; i++ {
		dp[i] = 1
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				dp[i] = max(dp[i], dp[j]+1)
			}
		}
		res = max(res, dp[i])
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

Greedy & Binary search:

```go
func lengthOfLIS(nums []int) int {
	d := make([]int, len(nums)+1)
	d[1] = nums[0]
	size := 1
	for _, x := range nums[1:] {
		if x > d[size] {
			size++
			d[size] = x
		} else {
			left, right := 1, size
			for left < right {
				mid := (left + right) >> 1
				if d[mid] >= x {
					right = mid
				} else {
					left = mid + 1
				}
			}
			if d[left] < x {
				left = 1
			}
			d[left] = x
		}
	}
	return size
}
```

Binary Indexed Tree:

```go
type BinaryIndexedTree struct {
	n int
	c []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	return &BinaryIndexedTree{n, c}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, val int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
		}
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) int {
	s := 0
	for x > 0 {
		if s < this.c[x] {
			s = this.c[x]
		}
		x -= this.lowbit(x)
	}
	return s
}

func lengthOfLIS(nums []int) int {
	s := make(map[int]bool)
	for _, v := range nums {
		s[v] = true
	}
	var t []int
	for v, _ := range s {
		t = append(t, v)
	}
	sort.Ints(t)
	m := make(map[int]int)
	for i, v := range t {
		m[v] = i + 1
	}
	ans := 1
	tree := newBinaryIndexedTree(len(m))
	for _, v := range nums {
		x := m[v]
		t := tree.query(x-1) + 1
		if ans < t {
			ans = t
		}
		tree.update(x, t)
	}
	return ans
}
```

### **Rust**

```rust
impl Solution {
    pub fn length_of_lis(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut dp = vec![1; n];
        for i in 1..n {
            for j in 0..i {
                if nums[i] > nums[j] {
                    dp[i] = dp[i].max(dp[j] + 1);
                }
            }
        }
        *dp.iter().max().unwrap()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
