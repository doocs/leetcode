# [1630. Arithmetic Subarrays](https://leetcode.com/problems/arithmetic-subarrays)

[中文文档](/solution/1600-1699/1630.Arithmetic%20Subarrays/README.md)

## Description

<p>A sequence of numbers is called <strong>arithmetic</strong> if it consists of at least two elements, and the difference between every two consecutive elements is the same. More formally, a sequence <code>s</code> is arithmetic if and only if <code>s[i+1] - s[i] == s[1] - s[0] </code>for all valid <code>i</code>.</p>

<p>For example, these are <strong>arithmetic</strong> sequences:</p>

<pre>
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9</pre>

<p>The following sequence is not <strong>arithmetic</strong>:</p>

<pre>
1, 1, 2, 5, 7</pre>

<p>You are given an array of <code>n</code> integers, <code>nums</code>, and two arrays of <code>m</code> integers each, <code>l</code> and <code>r</code>, representing the <code>m</code> range queries, where the <code>i<sup>th</sup></code> query is the range <code>[l[i], r[i]]</code>. All the arrays are <strong>0-indexed</strong>.</p>

<p>Return <em>a list of </em><code>boolean</code> <em>elements</em> <code>answer</code><em>, where</em> <code>answer[i]</code> <em>is</em> <code>true</code> <em>if the subarray</em> <code>nums[l[i]], nums[l[i]+1], ... , nums[r[i]]</code><em> can be <strong>rearranged</strong> to form an <strong>arithmetic</strong> sequence, and</em> <code>false</code> <em>otherwise.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = <code>[4,6,5,9,3,7]</code>, l = <code>[0,0,2]</code>, r = <code>[2,3,5]</code>
<strong>Output:</strong> <code>[true,false,true]</code>
<strong>Explanation:</strong>
In the 0<sup>th</sup> query, the subarray is [4,6,5]. This can be rearranged as [6,5,4], which is an arithmetic sequence.
In the 1<sup>st</sup> query, the subarray is [4,6,5,9]. This cannot be rearranged as an arithmetic sequence.
In the 2<sup>nd</sup> query, the subarray is <code>[5,9,3,7]. This</code> can be rearranged as <code>[3,5,7,9]</code>, which is an arithmetic sequence.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
<strong>Output:</strong> [false,true,false,false,true,true]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>m == l.length</code></li>
	<li><code>m == r.length</code></li>
	<li><code>2 &lt;= n &lt;= 500</code></li>
	<li><code>1 &lt;= m &lt;= 500</code></li>
	<li><code>0 &lt;= l[i] &lt; r[i] &lt; n</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def checkArithmeticSubarrays(
        self, nums: List[int], l: List[int], r: List[int]
    ) -> List[bool]:
        def check(nums, l, r):
            if r - l < 2:
                return True
            s = set(nums[l : r + 1])
            mx = max(nums[l : r + 1])
            mi = min(nums[l : r + 1])
            if (mx - mi) % (r - l) != 0:
                return False
            delta = (mx - mi) / (r - l)
            for i in range(1, r - l + 1):
                if (mi + delta * i) not in s:
                    return False
            return True

        return [check(nums, l[i], r[i]) for i in range(len(l))]
```

### **Java**

```java
class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; ++i) {
            res.add(check(nums, l[i], r[i]));
        }
        return res;
    }

    private boolean check(int[] nums, int l, int r) {
        if (r - l < 2) {
            return true;
        }
        Set<Integer> s = new HashSet<>();
        int mx = Integer.MIN_VALUE;
        int mi = Integer.MAX_VALUE;
        for (int i = l; i <= r; ++i) {
            s.add(nums[i]);
            mx = Math.max(mx, nums[i]);
            mi = Math.min(mi, nums[i]);
        }
        if ((mx - mi) % (r - l) != 0) {
            return false;
        }
        int delta = (mx - mi) / (r - l);
        for (int i = 1; i <= r - l; ++i) {
            if (!s.contains(mi + delta * i)) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<bool> checkArithmeticSubarrays(vector<int>& nums, vector<int>& l, vector<int>& r) {
        vector<bool> res;
        for (int i = 0; i < l.size(); ++i) {
            res.push_back(check(nums, l[i], r[i]));
        }
        return res;
    }

    bool check(vector<int>& nums, int l, int r) {
        if (r - l < 2) return true;
        unordered_set<int> s;
        int mx = -100010;
        int mi = 100010;
        for (int i = l; i <= r; ++i) {
            s.insert(nums[i]);
            mx = max(mx, nums[i]);
            mi = min(mi, nums[i]);
        }
        if ((mx - mi) % (r - l) != 0) return false;
        int delta = (mx - mi) / (r - l);
        for (int i = 1; i <= r - l; ++i) {
            if (!s.count(mi + delta * i)) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func checkArithmeticSubarrays(nums []int, l []int, r []int) []bool {
	n := len(l)
	var res []bool
	for i := 0; i < n; i++ {
		res = append(res, check(nums, l[i], r[i]))
	}
	return res
}

func check(nums []int, l, r int) bool {
	if r-l < 2 {
		return true
	}
	s := make(map[int]bool)
	mx, mi := -100010, 100010
	for i := l; i <= r; i++ {
		s[nums[i]] = true
		mx = max(mx, nums[i])
		mi = min(mi, nums[i])
	}
	if (mx-mi)%(r-l) != 0 {
		return false
	}
	delta := (mx - mi) / (r - l)
	for i := 1; i <= r-l; i++ {
		if !s[mi+delta*i] {
			return false
		}
	}
	return true
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function checkArithmeticSubarrays(
    nums: number[],
    l: number[],
    r: number[],
): boolean[] {
    const m = l.length;
    const res = new Array(m).fill(true);
    for (let i = 0; i < m; i++) {
        const arr = nums.slice(l[i], r[i] + 1).sort((a, b) => b - a);
        for (let j = 2; j < arr.length; j++) {
            if (arr[j - 2] - arr[j - 1] !== arr[j - 1] - arr[j]) {
                res[i] = false;
                break;
            }
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn check_arithmetic_subarrays(nums: Vec<i32>, l: Vec<i32>, r: Vec<i32>) -> Vec<bool> {
        let m = l.len();
        let mut res = vec![true; m];
        for i in 0..m {
            let mut arr = nums[l[i] as usize..=r[i] as usize].to_vec();
            arr.sort();
            for j in 2..arr.len() {
                if arr[j - 2] - arr[j - 1] != arr[j - 1] - arr[j] {
                    res[i] = false;
                    break;
                }
            }
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
