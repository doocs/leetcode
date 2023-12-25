# [2975. Maximum Square Area by Removing Fences From a Field](https://leetcode.com/problems/maximum-square-area-by-removing-fences-from-a-field)

[中文文档](/solution/2900-2999/2975.Maximum%20Square%20Area%20by%20Removing%20Fences%20From%20a%20Field/README.md)

## Description

<p>There is a large <code>(m - 1) x (n - 1)</code> rectangular field with corners at <code>(1, 1)</code> and <code>(m, n)</code> containing some horizontal and vertical fences given in arrays <code>hFences</code> and <code>vFences</code> respectively.</p>

<p>Horizontal fences are from the coordinates <code>(hFences[i], 1)</code> to <code>(hFences[i], n)</code> and vertical fences are from the coordinates <code>(1, vFences[i])</code> to <code>(m, vFences[i])</code>.</p>

<p>Return <em>the <strong>maximum</strong> area of a <strong>square</strong> field that can be formed by <strong>removing</strong> some fences (<strong>possibly none</strong>) or </em><code>-1</code> <em>if it is impossible to make a square field</em>.</p>

<p>Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9 </sup>+ 7</code>.</p>

<p><strong>Note: </strong>The field is surrounded by two horizontal fences from the coordinates <code>(1, 1)</code> to <code>(1, n)</code> and <code>(m, 1)</code> to <code>(m, n)</code> and two vertical fences from the coordinates <code>(1, 1)</code> to <code>(m, 1)</code> and <code>(1, n)</code> to <code>(m, n)</code>. These fences <strong>cannot</strong> be removed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2975.Maximum%20Square%20Area%20by%20Removing%20Fences%20From%20a%20Field/images/screenshot-from-2023-11-05-22-40-25.png" /></p>

<pre>
<strong>Input:</strong> m = 4, n = 3, hFences = [2,3], vFences = [2]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Removing the horizontal fence at 2 and the vertical fence at 2 will give a square field of area 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2900-2999/2975.Maximum%20Square%20Area%20by%20Removing%20Fences%20From%20a%20Field/images/maxsquareareaexample1.png" style="width: 285px; height: 242px;" /></p>

<pre>
<strong>Input:</strong> m = 6, n = 7, hFences = [2], vFences = [4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> It can be proved that there is no way to create a square field by removing fences.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= m, n &lt;= 10<sup>9</sup></code></li>
	<li><code><font face="monospace">1 &lt;= hF</font>ences<font face="monospace">.length, vFences.length &lt;= 600</font></code></li>
	<li><code><font face="monospace">1 &lt; hFences[i] &lt; m</font></code></li>
	<li><code><font face="monospace">1 &lt; vFences[i] &lt; n</font></code></li>
	<li><code><font face="monospace">hFences</font></code><font face="monospace"> and </font><code><font face="monospace">vFences</font></code><font face="monospace"> are unique.</font></li>
</ul>

## Solutions

**Solution 1: Enumeration**

We can enumerate any two horizontal fences $a$ and $b$ in $hFences$, calculate the distance $d$ between $a$ and $b$, and record it in the hash table $hs$. Then, we enumerate any two vertical fences $c$ and $d$ in $vFences$, calculate the distance $d$ between $c$ and $d$, and record it in the hash table $vs$. Finally, we traverse the hash table $hs$. If a distance $d$ in $hs$ also exists in the hash table $vs$, it means that there exists a square field with a side length of $d$ and an area of $d^2$. We just need to take the largest $d$ and calculate $d^2 \bmod 10^9 + 7$.

The time complexity is $O(h^2 + v^2)$, and the space complexity is $O(h^2 + v^2)$. Where $h$ and $v$ are the lengths of $hFences$ and $vFences$ respectively.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximizeSquareArea(
        self, m: int, n: int, hFences: List[int], vFences: List[int]
    ) -> int:
        def f(nums: List[int], k: int) -> Set[int]:
            nums.extend([1, k])
            nums.sort()
            return {b - a for a, b in combinations(nums, 2)}

        mod = 10**9 + 7
        hs = f(hFences, m)
        vs = f(vFences, n)
        ans = max(hs & vs, default=0)
        return ans**2 % mod if ans else -1
```

### **Java**

```java
class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> hs = f(hFences, m);
        Set<Integer> vs = f(vFences, n);
        hs.retainAll(vs);
        int ans = -1;
        final int mod = (int) 1e9 + 7;
        for (int x : hs) {
            ans = Math.max(ans, x);
        }
        return ans > 0 ? (int) (1L * ans * ans % mod) : -1;
    }

    private Set<Integer> f(int[] nums, int k) {
        int n = nums.length;
        nums = Arrays.copyOf(nums, n + 2);
        nums[n] = 1;
        nums[n + 1] = k;
        Arrays.sort(nums);
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                s.add(nums[i] - nums[j]);
            }
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximizeSquareArea(int m, int n, vector<int>& hFences, vector<int>& vFences) {
        auto f = [](vector<int>& nums, int k) {
            nums.push_back(k);
            nums.push_back(1);
            sort(nums.begin(), nums.end());
            unordered_set<int> s;
            for (int i = 0; i < nums.size(); ++i) {
                for (int j = 0; j < i; ++j) {
                    s.insert(nums[i] - nums[j]);
                }
            }
            return s;
        };
        auto hs = f(hFences, m);
        auto vs = f(vFences, n);
        int ans = 0;
        for (int h : hs) {
            if (vs.count(h)) {
                ans = max(ans, h);
            }
        }
        const int mod = 1e9 + 7;
        return ans > 0 ? 1LL * ans * ans % mod : -1;
    }
};
```

### **Go**

```go
func maximizeSquareArea(m int, n int, hFences []int, vFences []int) int {
	f := func(nums []int, k int) map[int]bool {
		nums = append(nums, 1, k)
		sort.Ints(nums)
		s := map[int]bool{}
		for i := 0; i < len(nums); i++ {
			for j := 0; j < i; j++ {
				s[nums[i]-nums[j]] = true
			}
		}
		return s
	}
	hs := f(hFences, m)
	vs := f(vFences, n)
	ans := 0
	for h := range hs {
		if vs[h] {
			ans = max(ans, h)
		}
	}
	if ans > 0 {
		return ans * ans % (1e9 + 7)
	}
	return -1
}
```

### **TypeScript**

```ts
function maximizeSquareArea(m: number, n: number, hFences: number[], vFences: number[]): number {
    const f = (nums: number[], k: number): Set<number> => {
        nums.push(1, k);
        nums.sort((a, b) => a - b);
        const s: Set<number> = new Set();
        for (let i = 0; i < nums.length; ++i) {
            for (let j = 0; j < i; ++j) {
                s.add(nums[i] - nums[j]);
            }
        }
        return s;
    };
    const hs = f(hFences, m);
    const vs = f(vFences, n);
    let ans = 0;
    for (const h of hs) {
        if (vs.has(h)) {
            ans = Math.max(ans, h);
        }
    }
    return ans ? Number(BigInt(ans) ** 2n % 1000000007n) : -1;
}
```

### **...**

```

```

<!-- tabs:end -->
