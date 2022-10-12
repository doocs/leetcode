# [1998. GCD Sort of an Array](https://leetcode.com/problems/gcd-sort-of-an-array)

[中文文档](/solution/1900-1999/1998.GCD%20Sort%20of%20an%20Array/README.md)

## Description

<p>You are given an integer array <code>nums</code>, and you can perform the following operation <strong>any</strong> number of times on <code>nums</code>:</p>

<ul>
	<li>Swap the positions of two elements <code>nums[i]</code> and <code>nums[j]</code> if <code>gcd(nums[i], nums[j]) &gt; 1</code> where <code>gcd(nums[i], nums[j])</code> is the <strong>greatest common divisor</strong> of <code>nums[i]</code> and <code>nums[j]</code>.</li>
</ul>

<p>Return <code>true</code> <em>if it is possible to sort </em><code>nums</code><em> in <strong>non-decreasing</strong> order using the above swap method, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,21,3]
<strong>Output:</strong> true
<strong>Explanation:</strong> We can sort [7,21,3] by performing the following operations:
- Swap 7 and 21 because gcd(7,21) = 7. nums = [<u><strong>21</strong></u>,<u><strong>7</strong></u>,3]
- Swap 21 and 3 because gcd(21,3) = 3. nums = [<u><strong>3</strong></u>,7,<u><strong>21</strong></u>]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,2,6,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to sort the array because 5 cannot be swapped with any other element.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,5,9,3,15]
<strong>Output:</strong> true
We can sort [10,5,9,3,15] by performing the following operations:
- Swap 10 and 15 because gcd(10,15) = 5. nums = [<u><strong>15</strong></u>,5,9,3,<u><strong>10</strong></u>]
- Swap 15 and 3 because gcd(15,3) = 3. nums = [<u><strong>3</strong></u>,5,9,<u><strong>15</strong></u>,10]
- Swap 10 and 15 because gcd(10,15) = 5. nums = [3,5,9,<u><strong>10</strong></u>,<u><strong>15</strong></u>]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def gcdSort(self, nums: List[int]) -> bool:
        n = 10**5 + 10
        p = list(range(n))
        f = defaultdict(list)
        mx = max(nums)
        for i in range(2, mx + 1):
            if f[i]:
                continue
            for j in range(i, mx + 1, i):
                f[j].append(i)

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i in nums:
            for j in f[i]:
                p[find(i)] = find(j)

        s = sorted(nums)
        for i, num in enumerate(nums):
            if s[i] != num and find(num) != find(s[i]):
                return False
        return True
```

### **Java**

```java
class Solution {
    private int[] p;

    public boolean gcdSort(int[] nums) {
        int n = 100010;
        p = new int[n];
        Map<Integer, List<Integer>> f = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int mx = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        for (int i = 2; i <= mx; ++i) {
            if (f.containsKey(i)) {
                continue;
            }
            for (int j = i; j <= mx; j += i) {
                f.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
            }
        }
        for (int i : nums) {
            for (int j : f.get(i)) {
                p[find(i)] = find(j);
            }
        }
        int[] s = new int[nums.length];
        System.arraycopy(nums, 0, s, 0, nums.length);
        Arrays.sort(s);
        for (int i = 0; i < nums.length; ++i) {
            if (s[i] != nums[i] && find(nums[i]) != find(s[i])) {
                return false;
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    bool gcdSort(vector<int>& nums) {
        int n = 100010;
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int mx = 0;
        for (auto num : nums) mx = max(mx, num);
        unordered_map<int, vector<int>> f;
        for (int i = 2; i <= mx; ++i) {
            if (!f[i].empty()) continue;
            for (int j = i; j <= mx; j += i) f[j].push_back(i);
        }
        for (int i : nums) {
            for (int j : f[i]) p[find(i)] = find(j);
        }
        vector<int> s = nums;
        sort(s.begin(), s.end());
        for (int i = 0; i < nums.size(); ++i) {
            if (s[i] != nums[i] && find(s[i]) != find(nums[i])) return false;
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
var p []int

func gcdSort(nums []int) bool {
	n := 100010
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	mx := 0
	for _, num := range nums {
		mx = max(mx, num)
	}
	f := make([][]int, mx+1)
	for i := 2; i <= mx; i++ {
		if len(f[i]) > 0 {
			continue
		}
		for j := i; j <= mx; j += i {
			f[j] = append(f[j], i)
		}
	}
	for _, i := range nums {
		for _, j := range f[i] {
			p[find(i)] = find(j)
		}
	}
	s := make([]int, len(nums))
	for i, num := range nums {
		s[i] = num
	}
	sort.Ints(s)
	for i, num := range nums {
		if s[i] != num && find(s[i]) != find(num) {
			return false
		}
	}
	return true
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
