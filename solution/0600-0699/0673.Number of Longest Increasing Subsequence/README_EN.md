# [673. Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence)

[中文文档](/solution/0600-0699/0673.Number%20of%20Longest%20Increasing%20Subsequence/README.md)

## Description

<p>Given an integer array&nbsp;<code>nums</code>, return <em>the number of longest increasing subsequences.</em></p>

<p><strong>Notice</strong> that the sequence has to be <strong>strictly</strong> increasing.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,4,7]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,2]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

similar problem: [LIS](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README_EN.md)

`cnt` array records the number of longest sequence ending in `nums[i]`

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findNumberOfLIS(self, nums: List[int]) -> int:
        maxLen, ans, n = 0, 0, len(nums)
        dp, cnt = [1] * n, [1] * n
        for i in range(n):
            for j in range(i):
                if nums[i] > nums[j]:
                    if dp[j] + 1 > dp[i]:
                        dp[i] = dp[j] + 1
                        cnt[i] = cnt[j]
                    elif dp[j] + 1 == dp[i]:
                        cnt[i] += cnt[j]
            if dp[i] > maxLen:
                maxLen = dp[i]
                ans = cnt[i]
            elif dp[i] == maxLen:
                ans += cnt[i]
        return ans
```

```python
class BinaryIndexedTree:
    def __init__(self, n):
        self.n = n
        self.c = [0] * (n + 1)
        self.d = [0] * (n + 1)

    @staticmethod
    def lowbit(x):
        return x & -x

    def update(self, x, val, cnt):
        while x <= self.n:
            if self.c[x] < val:
                self.c[x] = val
                self.d[x] = cnt
            elif self.c[x] == val:
                self.d[x] += cnt
            x += BinaryIndexedTree.lowbit(x)

    def query(self, x):
        val = cnt = 0
        while x:
            if self.c[x] > val:
                val = self.c[x]
                cnt = self.d[x]
            elif self.c[x] == val:
                cnt += self.d[x]
            x -= BinaryIndexedTree.lowbit(x)
        return val, cnt


class Solution:
    def findNumberOfLIS(self, nums: List[int]) -> int:
        s = sorted(set(nums))
        m = {v: i for i, v in enumerate(s, 1)}
        n = len(m)
        tree = BinaryIndexedTree(n)
        ans = 0
        for v in nums:
            x = m[v]
            val, cnt = tree.query(x - 1)
            tree.update(x, val + 1, max(cnt, 1))
        return tree.query(n)[1]
```

### **Java**

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int maxLen = 0, ans = 0, n = nums.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i];
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        TreeSet<Integer> ts = new TreeSet();
        for (int v : nums) {
            ts.add(v);
        }
        int idx = 1;
        Map<Integer, Integer> m = new HashMap<>();
        for (int v : ts) {
            m.put(v, idx++);
        }
        int n = m.size();
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int v : nums) {
            int x = m.get(v);
            int[] t = tree.query(x - 1);
            tree.update(x, t[0] + 1, Math.max(t[1], 1));
        }
        return tree.query(n)[1];
    }
}

class BinaryIndexedTree {
    private int n;
    private int[] c;
    private int[] d;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
        d = new int[n + 1];
    }

    public void update(int x, int val, int cnt) {
        while (x <= n) {
            if (c[x] < val) {
                c[x] = val;
                d[x] = cnt;
            } else if (c[x] == val) {
                d[x] += cnt;
            }
            x += lowbit(x);
        }
    }

    public int[] query(int x) {
        int val = 0;
        int cnt = 0;
        while (x > 0) {
            if (val < c[x]) {
                val = c[x];
                cnt = d[x];
            } else if (val == c[x]) {
                cnt += d[x];
            }
            x -= lowbit(x);
        }
        return new int[]{val, cnt};
    }

    public static int lowbit(int x) {
        return x & -x;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findNumberOfLIS(vector<int>& nums) {
        int maxLen = 0, ans = 0, n = nums.size();
        vector<int> dp(n, 1), cnt(n, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i];
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }
};
```

```cpp
class BinaryIndexedTree {
public:
    int n;
    vector<int> c;
    vector<int> d;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1), d(n + 1){}

    void update(int x, int val, int cnt) {
        while (x <= n)
        {
            if (c[x] < val)
            {
                c[x] = val;
                d[x] = cnt;
            }
            else if (c[x] == val) d[x] += cnt;
            x += lowbit(x);
        }
    }

    vector<int> query(int x) {
        int val = 0, cnt = 0;
        while (x > 0)
        {
            if (val < c[x])
            {
                val = c[x];
                cnt = d[x];
            }
            else if (val == c[x]) cnt += d[x];
            x -= lowbit(x);
        }
        return {val, cnt};
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class Solution {
public:
    int findNumberOfLIS(vector<int>& nums) {
        set<int> s(nums.begin(), nums.end());
        int idx = 1;
        unordered_map<int, int> m;
        for (int v : s) m[v] = idx++;
        int n = m.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        for (int v : nums)
        {
            int x = m[v];
            auto t = tree->query(x - 1);
            tree->update(x, t[0] + 1, max(t[1], 1));
        }
        return tree->query(n)[1];
    }
};
```

### **Go**

```go
func findNumberOfLIS(nums []int) int {
	maxLen, ans, n := 0, 0, len(nums)
	dp, cnt := make([]int, n), make([]int, n)
	for i := 0; i < n; i++ {
		dp[i] = 1
		cnt[i] = 1
		for j := 0; j < i; j++ {
			if nums[i] > nums[j] {
				if dp[j]+1 > dp[i] {
					dp[i] = dp[j] + 1
					cnt[i] = cnt[j]
				} else if dp[j]+1 == dp[i] {
					cnt[i] += cnt[j]
				}
			}
		}
		if dp[i] > maxLen {
			maxLen = dp[i]
			ans = cnt[i]
		} else if dp[i] == maxLen {
			ans += cnt[i]
		}
	}
	return ans
}
```

```go
type BinaryIndexedTree struct {
	n int
	c []int
	d []int
}

func newBinaryIndexedTree(n int) *BinaryIndexedTree {
	c := make([]int, n+1)
	d := make([]int, n+1)
	return &BinaryIndexedTree{n, c, d}
}

func (this *BinaryIndexedTree) lowbit(x int) int {
	return x & -x
}

func (this *BinaryIndexedTree) update(x, val, cnt int) {
	for x <= this.n {
		if this.c[x] < val {
			this.c[x] = val
			this.d[x] = cnt
		} else if this.c[x] == val {
			this.d[x] += cnt
		}
		x += this.lowbit(x)
	}
}

func (this *BinaryIndexedTree) query(x int) []int {
	var val, cnt int
	for x > 0 {
		if val < this.c[x] {
			val = this.c[x]
			cnt = this.d[x]
		} else if val == this.c[x] {
			cnt += this.d[x]
		}
		x -= this.lowbit(x)
	}
	return []int{val, cnt}
}

func findNumberOfLIS(nums []int) int {
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
	n := len(m)
	tree := newBinaryIndexedTree(n)
	for _, v := range nums {
		x := m[v]
		t := tree.query(x - 1)
		tree.update(x, t[0]+1, max(t[1], 1))
	}
	return tree.query(n)[1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_number_of_lis(nums: Vec<i32>) -> i32 {
        let mut max_len = 0;
        let mut ans = 0;
        let n = nums.len();
        let mut dp = vec![1; n];
        let mut cnt = vec![1; n];
        for i in 0..n {
            for j in 0..i {
                if nums[i] > nums[j] {
                    if dp[j] + 1 > dp[i] {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if dp[j] + 1 == dp[i] {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if dp[i] > max_len {
                max_len = dp[i];
                ans = cnt[i];
            } else if dp[i] == max_len {
                ans += cnt[i];
            }
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
