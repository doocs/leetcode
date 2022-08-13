# [2170. Minimum Operations to Make the Array Alternating](https://leetcode.com/problems/minimum-operations-to-make-the-array-alternating)

[中文文档](/solution/2100-2199/2170.Minimum%20Operations%20to%20Make%20the%20Array%20Alternating/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <code>n</code> positive integers.</p>

<p>The array <code>nums</code> is called <strong>alternating</strong> if:</p>

<ul>
	<li><code>nums[i - 2] == nums[i]</code>, where <code>2 &lt;= i &lt;= n - 1</code>.</li>
	<li><code>nums[i - 1] != nums[i]</code>, where <code>1 &lt;= i &lt;= n - 1</code>.</li>
</ul>

<p>In one <strong>operation</strong>, you can choose an index <code>i</code> and <strong>change</strong> <code>nums[i]</code> into <strong>any</strong> positive integer.</p>

<p>Return <em>the <strong>minimum number of operations</strong> required to make the array alternating</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,3,2,4,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
One way to make the array alternating is by converting it to [3,1,3,<u><strong>1</strong></u>,<u><strong>3</strong></u>,<u><strong>1</strong></u>].
The number of operations required in this case is 3.
It can be proven that it is not possible to make the array alternating in less than 3 operations. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,2,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
One way to make the array alternating is by converting it to [1,2,<u><strong>1</strong></u>,2,<u><strong>1</strong></u>].
The number of operations required in this case is 2.
Note that the array cannot be converted to [<u><strong>2</strong></u>,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        def get(i):
            c = Counter(nums[i::2]).most_common(2)
            if not c:
                return [(0, 0), (0, 0)]
            if len(c) == 1:
                return [c[0], (0, 0)]
            return c

        n = len(nums)
        return min(n - (n1 + n2) for a, n1 in get(0) for b, n2 in get(1) if a != b)
```

### **Java**

```java
class Solution {
    private int[] nums;
    private int n;

    public int minimumOperations(int[] nums) {
        this.nums = nums;
        n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int[] e1 : get(0)) {
            for (int[] e2 : get(1)) {
                if (e1[0] != e2[0]) {
                    ans = Math.min(ans, n - (e1[1] + e2[1]));
                }
            }
        }
        return ans;
    }

    private int[][] get(int i) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (; i < n; i += 2) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        int a = 0;
        int n1 = 0;
        int b = 0;
        int n2 = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int k = e.getKey();
            int v = e.getValue();
            if (v > n1) {
                b = a;
                n2 = n1;
                a = k;
                n1 = v;
            } else if (v > n2) {
                b = k;
                n2 = v;
            }
        }
        return new int[][]{{a, n1}, {b, n2}};
    }
}
```

### **TypeScript**

```ts
function minimumOperations(nums: number[]): number {
    const n = nums.length,
        m = 10 ** 5;
    let odd = new Array(m).fill(0);
    let even = new Array(m).fill(0);
    for (let i = 0; i < n; i++) {
        let cur = nums[i];
        if (i & 1) {
            odd[cur] = (odd[cur] || 0) + 1;
        } else {
            even[cur] = (even[cur] || 0) + 1;
        }
    }
    let i1 = odd.indexOf(Math.max(...odd));
    let i2 = even.indexOf(Math.max(...even));
    if (i1 != i2) {
        return n - odd[i1] - even[i2];
    } else {
        let l1 = odd[i1],
            l2 = even[i2];
        (odd[i1] = 0), (even[i2] = 0);
        let j1 = odd.indexOf(Math.max(...odd));
        let j2 = even.indexOf(Math.max(...even));
        return n - Math.max(l1 + even[j2], l2 + odd[j1]);
    }
}
```

### **C++**

```cpp
typedef pair<int, int> PII;

class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        int ans = INT_MAX;
        int n = nums.size();
        for (auto& [a, n1] : get(0, nums))
            for (auto& [b, n2] : get(1, nums))
                if (a != b)
                    ans = min(ans, n - (n1 + n2));
        return ans;
    }

    vector<PII> get(int i, vector<int>& nums) {
        unordered_map<int, int> freq;
        for (; i < nums.size(); i += 2) ++freq[nums[i]];
        int a = 0, n1 = 0, b = 0, n2 = 0;
        for (auto& [k, v] : freq) {
            if (v > n1) {
                b = a;
                n2 = n1;
                a = k;
                n1 = v;
            } else if (v > n2) {
                b = k;
                n2 = v;
            }
        }
        return {{a, n1}, {b, n2}};
    }
};
```

### **Go**

```go
func minimumOperations(nums []int) int {
	n := len(nums)
	get := func(i int) [][]int {
		freq := make(map[int]int)
		for ; i < n; i += 2 {
			freq[nums[i]]++
		}
		a, n1, b, n2 := 0, 0, 0, 0
		for k, v := range freq {
			if v > n1 {
				b, n2, a, n1 = a, n1, k, v
			} else if v > n2 {
				b, n2 = k, v
			}
		}
		return [][]int{{a, n1}, {b, n2}}
	}
	ans := 100000
	for _, e1 := range get(0) {
		for _, e2 := range get(1) {
			if e1[0] != e2[0] && ans > (n-(e1[1]+e2[1])) {
				ans = n - (e1[1] + e2[1])
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
